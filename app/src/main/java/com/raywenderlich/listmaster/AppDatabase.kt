package com.raywenderlich.listmaster

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.raywenderlich.listmaster.listcategory.ListCategory
import com.raywenderlich.listmaster.listcategory.ListCategoryDao

@Database(entities = [(ListCategory::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listCategoryDao() : ListCategoryDao
}