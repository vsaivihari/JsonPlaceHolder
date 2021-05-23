package com.firstorion.project.repo

import com.firstorion.project.database.LocalDataSource
import com.firstorion.project.network.NetworkDataSource
import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.User
import com.firstorion.project.viewmodel.post.IPostsRepo
import com.firstorion.project.viewmodel.user.IUsersRepo

class OrionRepository
constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) : IUsersRepo, IPostsRepo{

    override suspend fun getUserWithId(userId: Int): User? {
        val user = networkDataSource.getUserWithId(userId)
        localDataSource.saveUser(user)
        return localDataSource.getUserWithId(userId)
    }

    override suspend fun deleteAllUsers() {
        localDataSource.deleteAllUsers()
    }

    override suspend fun getAllPosts(): List<Post> {
        if (localDataSource.isPostsEmpty()) {
            val posts = networkDataSource.getAllPostsFromAllUsers()
            localDataSource.savePosts(posts)
        }
        return localDataSource.getAllPosts()
    }

    override suspend fun uploadPost(userId: Int, body: String, title: String) {
            networkDataSource.uploadPost(userId, body, title)
    }

    override suspend fun deleteAllPosts() {
        localDataSource.deleteAllPost()
    }


}