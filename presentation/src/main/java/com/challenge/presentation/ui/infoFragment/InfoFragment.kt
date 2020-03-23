package com.challenge.presentation.ui.infoFragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.presentation.R
import com.challenge.presentation.common.extention.gone
import com.challenge.presentation.common.extention.observe
import com.challenge.presentation.common.extention.visible
import com.challenge.presentation.databinding.FragmentDetailBinding
import com.challenge.presentation.ui.SharedViewModel
import com.challenge.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class InfoFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: SharedViewModel by lazy {
        activity.let {
            ViewModelProviders.of(it!!, viewModelFactory).get(SharedViewModel::class.java)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false)
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard(activity!!)
        constraint.gone()
        constraint.alpha = 0.0f
        observe(mainViewModel.infoCharacterLiveData, ::onGetCharacterInfo)
        mainViewModel.getCharacterInfo(arguments!!.getString("id", ""))
        ivBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun onGetCharacterInfo(resultState: ResultState<Entity.Character>) {
        when (resultState) {
            is ResultState.Error -> { Log.e("___errrrrr","onGetCharacterInfo")}
            is ResultState.Loading -> {}
            is ResultState.Success -> {

                binding.data = resultState.data

                constraint.animate()
                    .alpha(1f)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            constraint?.let {
                                constraint.visible()
                            }
                        }
                    })
            }
        }
    }
}






