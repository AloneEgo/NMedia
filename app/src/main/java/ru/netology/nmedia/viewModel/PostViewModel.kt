package ru.netology.nmedia.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likes = 0,
    likeByMe = false
)
class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<List<Post>> = repository.getAll()
    val edited = MutableLiveData(empty)

    fun like(id: Long) = repository.likeById(id)
    fun share(id: Long) = repository.share(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun save(text: String) {
        edited.value?.let {
            val content = text.trim()
            if (it.content != content) {
                repository.save(it.copy(content = content))
            }
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun cancelEdit() {
        edited.value = empty
    }
}