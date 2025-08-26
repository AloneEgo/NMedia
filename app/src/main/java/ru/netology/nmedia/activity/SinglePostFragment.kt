package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostViewHolder
import ru.netology.nmedia.databinding.FragmentSinglePostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewModel.PostViewModel

class SinglePostFragment : Fragment() {

    private val viewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSinglePostBinding.inflate(inflater, container, false)

        val postId = arguments?.getLong("postId") ?: -1

        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val post = posts.find { it.id == postId }
            if (post == null) {
                findNavController().navigateUp()
                return@observe
            }


            PostViewHolder(binding.postLayout, object : OnInteractionListener {

                override fun like(post: Post) {
                    viewModel.like(post.id)
                }

                override fun remove(post: Post) {
                    viewModel.removeById(post.id)
                    findNavController().navigateUp()
                }

                override fun edit(post: Post) {
                    viewModel.edit(post)
                    findNavController().navigate(
                        R.id.action_singlePostFragment_to_newPostFragment,
                        Bundle().apply { putString("textArg", post.content) }
                    )
                }

                override fun share(post: Post) {
                    viewModel.share(post.id)
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, post.content)
                    }
                    val chooser =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(chooser)
                }

                override fun onVideoPlay(post: Post) {
                    val intent = Intent(Intent.ACTION_VIEW, post.video?.toUri())
                    if (intent.resolveActivity(requireContext().packageManager) != null) {
                        startActivity(intent)
                    } else {
                        Toast.makeText(context, "Приложение не найдено", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPostClick(post: Post) {

                }

            }).bind(post)


        }

        return binding.root
    }
}