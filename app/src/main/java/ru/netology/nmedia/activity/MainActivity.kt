package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.util.NumberFormatter
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) { posts ->
            binding.content.removeAllViews()
            posts.map { post ->
                CardPostBinding.inflate(layoutInflater, binding.content, true).apply {
                    author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    likesCount.text = NumberFormatter.format(post.likes)
                    shareCount.text = NumberFormatter.format(post.shares)
                    viewsCount.text = NumberFormatter.format(post.views)
                    likes.setImageResource((if (post.likeByMe) R.drawable.ic_liked_24 else R.drawable.ic_favorite_24))
                    likes.setOnClickListener {
                        viewModel.like(post.id)
                    }
                }
            }
        }



//            binding.share.setOnClickListener {
//                viewModel.share()
//            }
    }
}