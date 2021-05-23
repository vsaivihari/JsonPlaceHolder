package com.firstorion.project.repo.post

/**
 * This interface needs to be implemented in the `database` package.
 * Return types and parameters can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IPostsDatabase {

    suspend fun getAllPosts(): List<Post>
    suspend fun savePosts(posts: List<Post>)
    suspend fun savePost(post: Post)
    suspend fun deleteAllPost()
    suspend fun deletePostWithId(postId: Int)
    suspend fun isPostsEmpty(): Boolean

}