package com.firstorion.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
class PostCacheEntity(
    @ColumnInfo(name = "userId")
    var userId: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "postId")
    var postId: Int,
    @ColumnInfo(name = "body")
    var body: String,
    @ColumnInfo(name = "title")
    var title: String
)

