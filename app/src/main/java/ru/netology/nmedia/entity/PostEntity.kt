package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likes: Int = 0,
    val shares: Int = 0,
    val views: Int = 0,
    val likeByMe: Boolean = false,
    val video: String? = null
) {
    fun toDto(): Post = Post(
        id = id,
        author = author,
        published = published,
        content = content,
        likes = likes,
        shares = shares,
        views = views,
        likeByMe = likeByMe,
        video = video
    )

//    companion object {
//        fun fromDto(post: Post): PostEntity = with(post) {
//            PostEntity(
//                id = id,
//                author = author,
//                published = published,
//                content = content,
//                likes = likes,
//                shares = shares,
//                views = views,
//                likeByMe = likeByMe,
//                video = video
//            )
//        }
//    }
}

fun Post.toEntity(): PostEntity = PostEntity(
    id = id,
    author = author,
    published = published,
    content = content,
    likes = likes,
    shares = shares,
    views = views,
    likeByMe = likeByMe,
    video = video
)