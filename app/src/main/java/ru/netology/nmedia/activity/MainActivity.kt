package ru.netology.nmedia.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.util.NumberFormatter
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "MainActivity hashcode: ${this.hashCode()}")
        Log.d("MainActivity", "onCreate")

        val viewModel: PostViewModel by viewModels()
        Log.d("MainActivity", "viewModel hashcode: ${viewModel.hashCode()}")

        viewModel.data.observe(this){post ->

            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesCount.text = NumberFormatter.format(post.likes)
                shareCount.text = NumberFormatter.format(post.shares)
                viewsCount.text = NumberFormatter.format(post.views)
                if (post.likeByMe) {
                    likes.setImageResource(R.drawable.ic_liked_24)
                } else {
                    likes.setImageResource(R.drawable.ic_favorite_24)
                }
            }

            binding.likes.setOnClickListener {
                viewModel.like()
            }

            binding.share.setOnClickListener {
                viewModel.share()
            }

        }
    }
}