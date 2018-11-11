/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.listmaster

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.raywenderlich.listmaster.listcategory.ListCategory
import com.raywenderlich.listmaster.listcategory.ListCategoryDao
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListCategoryDaoTest {

    // I don't know what InstantTaskExecutorRule() is
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var listCategoryDao: ListCategoryDao

    // In the initial setup, we get context and then create Database instance and DAO instance
    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getTargetContext()
        try {
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                    .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("TEST", e.message)
        }
        listCategoryDao = database.listCategoryDao()
    }


    @Test
    fun testAddingAndRetrievingData() {


        val preInsertRetrievedCategories = listCategoryDao.getAll() // This should get nothing because we don't have any data before test

        val listCategory = ListCategory("Cats", 1) // Create new Category object - Note: It takes 2 parameters
        listCategoryDao.insertAll(listCategory) // Use insert method of DAO to insert the object

        val postInsertRetrievedCategories = listCategoryDao.getAll() // now get all objects again
        val sizeDifference = postInsertRetrievedCategories.size - preInsertRetrievedCategories.size // This difference should be (1 = 1 - 0)
        Assert.assertEquals(1, sizeDifference) // to Ensure sizeDifference is 1
        val retrievedCategory = postInsertRetrievedCategories.last() // This should return List<ListCategory> and we use last to retrieve last object/category
        Assert.assertEquals("Cats", retrievedCategory.categoryName)
    }

    @After
    fun tearDown() {
        database.close() // close database after test is finished
    }

}