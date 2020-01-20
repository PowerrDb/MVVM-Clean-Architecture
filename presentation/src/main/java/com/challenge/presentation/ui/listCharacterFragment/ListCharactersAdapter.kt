package com.challenge.presentation.ui.listCharacterFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.domain.entity.Entity
import com.challenge.presentation.R
import com.challenge.presentation.databinding.ItemCharacterBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class ListCharactersAdapter :
    PagedListAdapter<Entity.Character, ListCharactersAdapter.ViewHolder>(
        diffCallback
    ) {
    private lateinit var binding: ItemCharacterBinding
    private val onItemClickSubject = PublishSubject.create<String>()
    val itemClickEvent: Observable<String> = onItemClickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = getItem(position)
        holder.bind(list)
    }


    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init{
            binding.setClickListener {
                binding.model?.let { model ->
                    it.findNavController().navigate(
                        R.id.action_fragment_character_to_fragment_detail,
                        bundleOf("id" to model.id.toString()))

                    onItemClickSubject.onNext("")

                }
            }
        }


        fun bind(item: Entity.Character?) {

            binding.apply {
                model=item
                executePendingBindings()
            }

        }


    }


    companion object {

        private val diffCallback: DiffUtil.ItemCallback<Entity.Character> =
            object : DiffUtil.ItemCallback<Entity.Character>() {

                override fun areItemsTheSame(
                    oldItem: Entity.Character,
                    newItem: Entity.Character
                ): Boolean {
                    return oldItem.id==newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Entity.Character,
                    newItem: Entity.Character
                ): Boolean {
                    return oldItem.id==newItem.id
                }
            }
    }
}