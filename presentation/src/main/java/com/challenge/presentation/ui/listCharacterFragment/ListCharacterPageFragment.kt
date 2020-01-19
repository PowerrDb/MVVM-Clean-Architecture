package com.challenge.presentation.ui.listCharacterFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.challenge.presentation.R
import com.challenge.presentation.databinding.FragmentCharactersBinding
import com.challenge.presentation.ui.base.BaseFragment
import javax.inject.Inject


class ListCharacterPageFragment : BaseFragment(), LifecycleOwner {
    private lateinit var binding: FragmentCharactersBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: ListCharacterViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListCharacterViewModel::class.java)
    }

    private val adapter: ListCharactersAdapter by lazy { ListCharactersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate<FragmentCharactersBinding>(
            inflater,
            R.layout.fragment_characters,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }


    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}










