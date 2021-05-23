package com.firstorion.project.database

import com.firstorion.project.repo.post.IPostsDatabase
import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.IUsersDatabase
import com.firstorion.project.repo.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(db: OrionDatabase) : IPostsDatabase, IUsersDatabase {

    private val postDAO = db.postDAo()
    private val userDAO = db.userDao()
    private val postCacheMapper = PostCacheMapper()
    private val userCacheMapper = UserCacheMapper()

    override suspend fun isPostsEmpty(): Boolean =
        withContext(Dispatchers.IO) { postDAO.postCount() <= 0 }

    override suspend fun getAllPosts(): List<Post> {
        return withContext(Dispatchers.IO) {
            val list = postDAO.getAllPosts()
            postCacheMapper.mapFromEntityList(list)
        }
    }

    override suspend fun isUsersEmpty(): Boolean {
        return withContext(Dispatchers.IO) { userDAO.userCount() <= 0 }
    }

    override suspend fun savePosts(posts: List<Post>) {
        withContext(Dispatchers.IO) {
            postDAO.savePosts(posts = postCacheMapper.mapToEntityList(posts))
        }
    }

    override suspend fun savePost(post: Post) {
        withContext(Dispatchers.IO) {
            postDAO.savePost(post = postCacheMapper.mapToEntity(post))
        }
    }

    override suspend fun deleteAllPost() {
        withContext(Dispatchers.IO) { postDAO.deleteAllPost() }
    }

    override suspend fun deletePostWithId(postId: Int) {
        withContext(Dispatchers.IO) { postDAO.deletePostWithId(postId) }
    }

    override suspend fun getUserWithId(userId: Int): User {
        return withContext(Dispatchers.IO) {
            val userCacheEntity = userDAO.getUserWithId(userId)
            userCacheMapper.mapFromEntity(userCacheEntity)
        }
    }

    override suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) {
            userDAO.saveUser(user = userCacheMapper.mapToEntity(user))
        }
    }

    override suspend fun deleteUserWithId(userId: Int) {
        withContext(Dispatchers.IO) { userDAO.deleteUserWithId(userId) }
    }

    override suspend fun deleteAllUsers() {
        withContext(Dispatchers.IO) { userDAO.deleteAllUsers() }
    }

}