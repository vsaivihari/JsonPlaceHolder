package com.firstorion.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostCacheEntity::class, UserCacheEntity::class], version = 1, exportSchema = false)
abstract class OrionDatabase : RoomDatabase() {

    abstract fun postDAo(): PostDAO
    abstract fun userDao(): UserDAO

    companion object {
        val DATABASE_NAME = "post_db"

        @Volatile
        private var INSTANCE: OrionDatabase? = null


        fun getDatabase(context: Context): OrionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrionDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}