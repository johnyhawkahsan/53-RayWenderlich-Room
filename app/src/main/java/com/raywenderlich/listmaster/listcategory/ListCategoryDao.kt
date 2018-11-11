package com.raywenderlich.listmaster.listcategory

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ListCategoryDao {
    @Query("SELECT * FROM list_categories")
    fun getAll() : List<ListCategory>

    @Insert
    fun insertAll(vararg listCategories: ListCategory)

}