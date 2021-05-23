package com.firstorion.project

import android.app.Application
import com.firstorion.project.database.LocalDataSource
import com.firstorion.project.database.OrionDatabase
import com.firstorion.project.network.NetworkDataSource
import com.firstorion.project.repo.OrionRepository

class OrionApplication: Application() {

    val database by lazy { OrionDatabase.getDatabase(this) }

    val repository by lazy { OrionRepository(LocalDataSource(database), NetworkDataSource() ) }
}