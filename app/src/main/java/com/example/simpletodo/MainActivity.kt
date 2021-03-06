package com.example.simpletodo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.simpletodo.TaskItemAdapter.OnClickListener
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import android.content.Intent as Intent



class MainActivity : AppCompatActivity() {
    //add a variable list to hold all the task
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            //current text
            var curr = data?.getStringExtra("keytext").toString()
            //current position of text
            var cpos = data?.getIntExtra("position", 0)!!
            //change the old task to the current task using its position
            listOfTasks.set(cpos, curr)
            //update the display
            adapter.notifyItemChanged(cpos)
            saveItems()

        }
    }

    fun onClickNext(position: Int) { //view:View
        //resultLauncher.launch(Intent(this, EditTask::class.java))
        val i = Intent(this,EditTask::class.java)
        //pass the position of the task clicked
        i.putExtra("position",position)
        //pass the text clicked from the task lists
        i.putExtra("keytext", listOfTasks.get(position))
        //launches the edit acitivity
        resultLauncher.launch(i)



    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onClickListener = object: TaskItemAdapter.OnClickListener{
            override fun onItemClicked(position: Int) {
                onClickNext(position);
            }
        }

        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //1.Remove item from list
                listOfTasks.removeAt(position)
                //2.When data set has changed, notify the adapter
                adapter.notifyDataSetChanged()

                saveItems()
            }

        }





//



        loadItems()

        //Look up recycler View in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //Create adapter passing in sample user data
        adapter = TaskItemAdapter(listOfTasks,onLongClickListener,onClickListener)
        //Attach adapter to recycler view to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        /**When the user enters a task, have the text(input) and button field set up
         * so the task can be added to the list
         */
        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        //Get button reference
        //Set on onclickListener
        findViewById<Button>(R.id.button).setOnClickListener{
            //1. Grab user's input text into @addTaskField
            val userInputtedTask = inputTextField.text.toString()
            //2. Add string to our task list: listOfTasks
            listOfTasks.add(userInputtedTask)

            //Once there's an update ot the list, notify the adapter
            adapter.notifyItemInserted(listOfTasks.size-1)

            //3.Reset text field
            inputTextField.setText("")

            saveItems()

        }






    }




    //Save user's data
    //From the file, read and write the data to save it

    //To get the file we need, create a method
    fun getDataFile(): File {
        //A specific task from the list is repr by every line
        return File(filesDir, "data.txt")
    }

    //Read every line in the data file to load
    fun loadItems(){
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch(ioException: IOException){
            ioException.printStackTrace()

       }

    }

    //Write the items into data file to save
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }


}