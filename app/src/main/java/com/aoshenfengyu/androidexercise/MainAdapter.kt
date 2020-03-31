package com.aoshenfengyu.androidexercise

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.aoshenfengyu.androidexercise.databinding.ViewHolderMainItemBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_main_item.view.*

class MainAdapter(val context: Context) :
    PagedListAdapter<GithubUser, MainItemViewHolder>(GithubUser.DiffUtils) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainItemViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ViewHolderMainItemBinding.inflate(layoutInflater, viewGroup, false)
        return MainItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, position)
    }

    override fun onViewRecycled(holder: MainItemViewHolder) {
        Glide.with(holder.itemView.context).clear(holder.itemView.view_holder_main_item_image)
        super.onViewRecycled(holder)
    }
}
