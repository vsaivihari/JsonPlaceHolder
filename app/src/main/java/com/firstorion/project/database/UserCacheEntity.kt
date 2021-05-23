package com.firstorion.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userId")
    var userId: Int,
    @ColumnInfo(name = "userName")
    var userName: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "email")
    var email: String
)