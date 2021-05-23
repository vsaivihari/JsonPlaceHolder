package com.firstorion.project.network

import com.firstorion.project.repo.post.IPostsNetwork
import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.IUsersNetwork
import com.firstorion.project.repo.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSource : IPostsNetwork, IUsersNetwork {

    private val postNetworkMapper = PostNetworkMapper()
    private val userNetworkMapper = UserNetworkMapper()

    override suspend fun getAllPostsFromAllUsers(): List<Post> {
        return withContext(Dispatchers.IO) {
            val list = OrionApi.orionService.getAllPostsFromAllUsers()
            postNetworkMapper.mapFromEntityList(list)
        }
    }


    override suspend fun uploadPost(userId: Int, title: String, body: String): Post {
        return withContext(Dispatchers.IO) {
            val postNetworkEntity = OrionApi.orionService.uploadPost(userId, title, body)
            postNetworkMapper.mapFromEntity(postNetworkEntity)
        }
    }

    override suspend fun getUserWithId(userId: Int): User {
        return withContext(Dispatchers.IO) {
            val userNetworkEntity = OrionApi.orionService.getUserWithId(userId)
            userNetworkMapper.mapFromEntity(userNetworkEntity)
        }
    }
}