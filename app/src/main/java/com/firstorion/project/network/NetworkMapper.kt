package com.firstorion.project.network

import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.User
import com.firstorion.project.util.EntityMapper

class PostNetworkMapper: EntityMapper<PostNetworkEntity, Post> {
    override fun mapFromEntity(entity: PostNetworkEntity): Post {
        return Post(
            userId = entity.userId,
            postId = entity.postId,
            body = entity.body,
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: Post): PostNetworkEntity {
        return PostNetworkEntity(
            userId = domainModel.userId,
            postId = domainModel.postId,
            body = domainModel.body,
            title = domainModel.title
        )
    }

    fun mapFromEntityList(entities: List<PostNetworkEntity>): List<Post> {
        return entities.map { mapFromEntity(it) }
    }

}

class UserNetworkMapper: EntityMapper<UserNetworkEntity, User> {
    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
            userId = entity.userId,
            name = entity.name,
            userName = entity.userName,
            email = entity.email
        )
    }

    override fun mapToEntity(domainModel: User): UserNetworkEntity {
        return UserNetworkEntity(
            userId = domainModel.userId,
            name = domainModel.name,
            userName = domainModel.userName,
            email = domainModel.email
        )
    }

    fun mapFromEntityList(entities: List<UserNetworkEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }
}