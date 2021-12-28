package com.example.simpletodo

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodo.databinding.ActivityEditTask2Binding
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class EditTask : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityEditTask2Binding

    var editTask = mutableListOf<String>()
    lateinit var adapter: EditTaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //added from last git save
        binding = ActivityEditTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        //saveItems()


        loadItems()

        //Look up recycler View in layout
        val recyclerView = findViewById<RecyclerView>(R.id.EditRecyclerView)
        //Create adapter passing in sample user data
        adapter = EditTaskAdapter(editTask)
        //Attach adapter to recycler view to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)

        //access passed data
        val needToEdit = getIntent().getStringExtra("position").toString()
        //retrieve the data and put into edit task list
        editTask.add(needToEdit)

        //Once there's an update ot the list, notify the adapter
        adapter.notifyItemInserted(editTask.size - 1)
    }

//Save user's data
    //From the file, read and write the data to save it

    //To get the file we need, create a method
    fun getDataFile(): File {
        //A specific task from the list is repr by every line
        return File(filesDir, "data.txt")
    }

    //Read every line in the data file to load
    fun loadItems() {
        try {
            editTask = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()

        }

    }

    //Write the items into data file to save
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), editTask)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }
}


