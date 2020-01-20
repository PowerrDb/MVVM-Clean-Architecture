package com.challenge.presentation.ui.listCharacterFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.presentation.R
import com.challenge.presentation.common.extention.observe
import com.challenge.presentation.common.extention.removeObserver
import com.challenge.presentation.databinding.FragmentCharactersBinding
import com.challenge.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject


class ListCharacterPageFragment : BaseFragment(), LifecycleOwner {
    private lateinit var binding: FragmentCharactersBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: ListCharacterViewModel by lazy {
        activity.let {
            ViewModelProviders.of(it!!, viewModelFactory).get(ListCharacterViewModel::class.java)
        }
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
        rvCharacters.adapter = adapter
        mainViewModel.getCharacters()

        observe(mainViewModel.charactersLiveData,::onGetCharacters)
        adapter.itemClickEvent.subscribe {
          //do when item click
        }
    }

    private fun onGetCharacters(resultState: ResultState<PagedList<Entity.Character>>) {
        Log.e("__onGetCharactessrs",resultState.toString())
        when (resultState) {
            is ResultState.Success -> {
                Log.e("__charSucc", resultState.toString())
                adapter.submitList(resultState.data)
            }
            is ResultState.Loading -> {Log.e("__charLoad", resultState.toString()) }
                is ResultState.Error ->{
                    Log.e("__charErro", resultState.toString())

                }
            }
        }
    }











