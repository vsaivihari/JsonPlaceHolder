package com.firstorion.project.repo.user

/**
 * This interface needs to be implemented in the `database` package.
 * Return types and parameters can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IUsersDatabase {

    suspend fun getUserWithId(userId: Int): User?
    suspend fun saveUser(user: User)
    suspend fun deleteUserWithId(userId: Int)
    suspend fun deleteAllUsers()
    suspend fun isUsersEmpty(): Boolean
}