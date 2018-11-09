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

package com.raywenderlich.listmaster.listcategory

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.raywenderlich.listmaster.R
import com.raywenderlich.listmaster.databinding.ActivityListCategoriesBinding
import com.raywenderlich.listmaster.databinding.DialogAddCategoryBinding

class ListCategoriesActivity : AppCompatActivity() {

  private lateinit var activityListsBinding: ActivityListCategoriesBinding
  private lateinit var listCategoryAdapter: ListCategoryAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityListsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_categories)

    // call our function to set up the add fab button logic
    setupAddButton()
  }

  /**
   * sets up the Fab button to launch a dialog allowing the user to add an item to their list.
   */
  private fun setupAddButton() {
    activityListsBinding.fab.setOnClickListener {

      // Set up the dialog
      val alertDialogBuilder = AlertDialog.Builder(this)
      alertDialogBuilder.setTitle("Title")
      val dialogAddCategoryBinding = DialogAddCategoryBinding.inflate(layoutInflater)

      alertDialogBuilder.setView(dialogAddCategoryBinding.root)

      // Set up the view model that the dialog input fields are bound to
      val listCategoryViewModel = ListCategoryViewModel()
      dialogAddCategoryBinding.listCategoryViewModel = listCategoryViewModel

      // Set up the positive and negative buttons. When the user clicks 'OK', a record is added to the
      // DB, the DB is queried, and the RecyclerView is updated.
      alertDialogBuilder.setPositiveButton(android.R.string.ok, { dialog: DialogInterface, which: Int ->
        AsyncTask.execute({
          runOnUiThread { }
        })
      })
      alertDialogBuilder.setNegativeButton(android.R.string.cancel, { dialog: DialogInterface, which: Int -> })
      alertDialogBuilder.show()
    }
  }
}
