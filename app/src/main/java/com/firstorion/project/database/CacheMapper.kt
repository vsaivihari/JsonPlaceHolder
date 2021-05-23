package com.firstorion.project.database

import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.User
import com.firstorion.project.util.EntityMapper

class PostCacheMapper: EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity): Post {
        return Post(
            userId = entity.userId,
            postId = entity.postId,
            body = entity.body,
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            userId = domainModel.userId,
            postId = domainModel.postId,
            body = domainModel.body,
            title = domainModel.title
        )
    }

    fun mapFromEntityList(entities: List<PostCacheEntity>): List<Post> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<Post>): List<PostCacheEntity> {
        return entities.map { mapToEntity(it) }
    }
}

class UserCacheMapper: EntityMapper<UserCacheEntity, User> {
    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(
            userId = entity.userId,
            name = entity.name,
            userName = entity.userName,
            email = entity.email
        )
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {
        return UserCacheEntity(
            userId = domainModel.userId,
            name = domainModel.name,
            userName = domainModel.userName,
            email = domainModel.email
        )
    }

    fun mapFromEntityList(entities: List<UserCacheEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }
}
