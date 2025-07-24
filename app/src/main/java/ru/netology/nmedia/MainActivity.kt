package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.NumberFormatter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
                id = 1,
                author = "Нетология. Университет интернет-профессий будущего",
                published= "21 мая в 18:36",
                content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                likes = 999,
                shares = 10,
                views = 1_200_000
        )

        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCount.text = NumberFormatter.format(post.likes)
            shareCount.text = NumberFormatter.format(post.shares)
            viewsCount.text = NumberFormatter.format(post.views)

            likes.setImageResource(R.drawable.ic_favorite_24)
            likes.setOnClickListener {
                post.likeByMe = !post.likeByMe
                if (post.likeByMe){
                    likes.setImageResource(R.drawable.ic_liked_24)
                    post.likes++
                } else{
                    likes.setImageResource(R.drawable.ic_favorite_24)
                    post.likes--
                }
                likesCount.text = NumberFormatter.format(post.likes)
            }

            share.setOnClickListener {
                post.shares++
                shareCount.text = NumberFormatter.format(post.shares)
            }
        }

    }
}