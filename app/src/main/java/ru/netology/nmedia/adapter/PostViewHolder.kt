package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ItemPostBinding
import kotlin.math.floor

class PostViewHolder(
    private val binding: ItemPostBinding,
    private val listener: PostAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        if (!post.video.isNullOrEmpty()){
            binding.videoGroup.visibility = Group.VISIBLE
        }
        binding.apply {
            avatarIv.setImageResource(R.drawable.avatar)
            authorTv.text = post.author
            publishedTv.text = post.published
            textTv.text = post.content
            like.isChecked = post.likedByMe
            like.text = setRoundCount(post.likeCount)
            share.text = setRoundCount(post.shareCount)

            like.setOnClickListener {
                listener.onLikeClicked(post)
            }
            share.setOnClickListener {
                listener.onShareClicked(post)
            }
//            video.setOnClickListener{
//                listener.onVideoClicked(post)
//            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                listener.onRemoveClicked(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEditClicked(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

    private fun setRoundCount(value: Int): String {
        return when (value) {
            0 -> ""
            in 1..999 -> value.toString()
            in 1000..1099 -> "1K"
            in 1100..9999 -> (floor(value.toDouble() / 1000 * 10f) / 10f).toString() + "K"
            in 10000..999_999 -> floor(value.toDouble() / 1000).toInt().toString() + "K"
            else -> (floor(value.toDouble() / 1_000_000 * 10f) / 10f).toString() + "M"
        }
    }
}