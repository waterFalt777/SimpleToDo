package com.example.simpletodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
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




        loadItem()

        //access passed data
        val needToEdit = getIntent().getIntExtra("position",0)

        //Look up recycler View in layout
        val recyclerView = findViewById<RecyclerView>(R.id.EditRecyclerView)
        //Create adapter passing in sample user data
        adapter = EditTaskAdapter(mutableListOf(editTask.get(needToEdit)))
        //Attach adapter to recycler view to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        //retrieve the data and put into edit task list
        //editTask.add(needToEdit)

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

    //Read only the line in the data file to load
    fun loadItem() {
        try {
            editTask = FileUtils.readLines(getDataFile(), Charset.defaultCharset())

        } catch (ioException: IOException) {
            ioException.printStackTrace()

        }

    }

    //DO I NEED TO Write the items into data file to save?
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), editTask)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }
}


