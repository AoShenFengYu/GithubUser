package com.aoshenfengyu.androidexercise

import androidx.recyclerview.widget.RecyclerView
import com.aoshenfengyu.androidexercise.databinding.ViewHolderMainItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_holder_main_item.view.*

class MainItemViewHolder(
    val binding: ViewHolderMainItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.executePendingBindings()
    }

    fun bind(githubUser: GithubUser, position: Int) {
        binding.item = githubUser
        binding.position = position
        binding.executePendingBindings()

        Glide.with(itemView.context)
            .load(githubUser.avatar_url)
            .placeholder(R.drawable.ic_photo_placeholder)
            .apply(
                RequestOptions()
                    .skipMemoryCache(true)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .fitCenter()
            )
            .into(itemView.view_holder_main_item_image)
    }

}
