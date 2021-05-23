package com.firstorion.project.repo.post

/**
 * The interface that will provide the network communication between https://jsonplaceholder.typicode.com/posts and the app
 *
 * This interface needs to be implemented in the `network` package.
 * Return types and parameters of the functions can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IPostsNetwork {

    suspend fun getAllPostsFromAllUsers(): List<Post>
    suspend fun uploadPost(userId: Int, title: String, body: String): Post

}