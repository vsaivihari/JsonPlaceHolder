package com.firstorion.project.viewmodel.post

import androidx.lifecycle.*
import com.firstorion.project.repo.OrionRepository
import com.firstorion.project.repo.post.Post
import kotlinx.coroutines.launch

/**
 * This class will get the `Post` data from `postRepo` that will be displayed on `PostsFragment` fragment.
 *
 * Please do not remove postsRepo from the constructor.
 * */
class PostsViewModel(
    private val postsRepo: IPostsRepo
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts



    fun getPosts() {
        viewModelScope.launch {
                _posts.value = postsRepo.getAllPosts()
        }
    }

}

class PostsViewModelFactory(private val repository: OrionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}