package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.NumberFormatter

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCount.text = NumberFormatter.format(post.likes)
            shareCount.text = NumberFormatter.format(post.shares)
            viewsCount.text = NumberFormatter.format(post.views)
            likes.setImageResource(
                (if (post.likeByMe) R.drawable.ic_liked_24 else R.drawable.ic_favorite_24)
            )
            likes.setOnClickListener {
                onLikeListener(post)
            }
        }
    }
}