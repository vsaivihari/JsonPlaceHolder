package com.firstorion.project.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserWithId(userId: Int): UserCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserCacheEntity)

    @Query("DELETE FROM users WHERE userId = :userId")
    suspend fun deleteUserWithId(userId: Int)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT COUNT(userId) FROM users")
    suspend fun userCount(): Int
}