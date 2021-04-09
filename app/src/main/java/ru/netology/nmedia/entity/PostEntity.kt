package ru.netology.nmedia.entity

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val likeCount: Int = 0,
    val shareCount: Int = 0
) {
    fun toDto() =
        Post(
            id = id,
            author = author,
            content = content,
            published = published,
            likedByMe = likedByMe,
            likeCount = likeCount,
            shareCount = shareCount
        )

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.content,
                dto.published,
                dto.likedByMe,
                dto.likeCount,
                dto.shareCount
            )
    }
}