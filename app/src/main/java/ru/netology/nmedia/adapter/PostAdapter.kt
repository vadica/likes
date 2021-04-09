package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.netology.nmedia.Post
import ru.netology.nmedia.databinding.ItemPostBinding
import android.widget.CheckBox



class PostAdapter(
    private val listener: PostAdapterClickListener
) : ListAdapter<Post, PostViewHolder>(PostDiffItemCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    object PostDiffItemCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem == newItem
    }
}