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
import com.challenge.presentation.common.extention.gone
import com.challenge.presentation.common.extention.observe
import com.challenge.presentation.common.extention.toObservable
import com.challenge.presentation.common.extention.visible
import com.challenge.presentation.databinding.FragmentCharactersBinding
import com.challenge.presentation.ui.SharedViewModel
import com.challenge.presentation.ui.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_characters.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ListCharcaterFragment : BaseFragment(), LifecycleOwner {
    private lateinit var binding: FragmentCharactersBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val sharedViewModel: SharedViewModel by lazy {
        activity.let {
            ViewModelProviders.of(it!!, viewModelFactory).get(SharedViewModel::class.java)
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
            false)
        binding.lifecycleOwner = this
        return binding.root
    }
    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCharacters.adapter = adapter
        observe(sharedViewModel.charactersLiveData, ::onGetCharacters)
       observeSearchBar()
    }

    @SuppressLint("CheckResult")
    private fun observeSearchBar() {
        //Search user input with delay
        binding.searchBar.toObservable()
            .debounce(700, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { sharedViewModel.getCharacters(it) }
    }

    private fun onGetCharacters(resultState: ResultState<PagedList<Entity.Character>>) {
        when (resultState) {
            is ResultState.Error -> {
                Log.e("Error","Error")
            }
            is ResultState.Success -> {

                if (resultState.data.size==0)binding.notFound.visible() else binding.notFound.gone()
                adapter.submitList(resultState.data)
                adapter.notifyDataSetChanged()
                pbCharacters.gone()
                rvCharacters.visible() }

            is ResultState.Loading -> {
                pbCharacters.visible()
                rvCharacters.gone()
            }

        }
    }
}











