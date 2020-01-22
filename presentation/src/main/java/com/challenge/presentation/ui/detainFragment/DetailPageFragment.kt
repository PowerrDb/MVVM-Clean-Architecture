package com.challenge.presentation.ui.detainFragment

import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ViewGroupBindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.presentation.R
import com.challenge.presentation.common.extention.gone
import com.challenge.presentation.common.extention.observe
import com.challenge.presentation.common.extention.visible
import com.challenge.presentation.databinding.FragmentDetailBinding
import com.challenge.presentation.ui.base.BaseFragment
import com.challenge.presentation.ui.listCharacterFragment.ListCharacterViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject
import android.animation.Animator
import android.inputmethodservice.Keyboard


class DetailPageFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: ListCharacterViewModel by lazy {
        activity.let {
            ViewModelProviders.of(it!!, viewModelFactory).get(ListCharacterViewModel::class.java)
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
            false
        )


        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard(activity!!)
        constraint.gone()
        constraint.alpha = 0.0f

        observe(mainViewModel.InfoCharacterLiveData,::onGetCharacterInfo)
        mainViewModel.getCharacterInfo(arguments!!.getString("id",""))
    }

    private fun onGetCharacterInfo(resultState: ResultState<Entity.Character>) {

        when (resultState) {
            is ResultState.Error ->{}
            is ResultState.Loading ->{}
            is ResultState.Success ->{

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






