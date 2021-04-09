package ru.netology.nmedia

import android.widget.ImageView

data class Post(
    val id: Long,
    val author: String,
    val authorAvatar: ImageView? = null,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val likeCount: Int = 0,
    val shareCount: Int = 0,
    val video: String? = null
)
