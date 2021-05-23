package com.firstorion.project.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDAO {


    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostCacheEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun savePosts(posts: List<PostCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun savePost(post: PostCacheEntity)

    @Query("DELETE FROM posts")
    suspend fun deleteAllPost()

    @Query("DELETE FROM posts WHERE postId = :postId")
    suspend fun deletePostWithId(postId: Int)

    @Query("SELECT COUNT(postId) FROM posts")
    suspend fun postCount(): Int

}