package com.example.simpletodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodo.databinding.ActivityEditTask2Binding
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
/**
 * The second activity that displays an edit screen
 * to change the task of the item clicked on the main screen
 */
class EditTask : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityEditTask2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTask2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        //edit text entry
        val edit = findViewById<EditText>(R.id.editText)

        //get the task clicked on, to edit
        var prev = intent.getStringExtra("keytext")

        //show the previous task
        edit.setHint(prev)

        //button to click once new task to replace the old has been entered
        val edbutton = findViewById<Button>(R.id.EditButton)

        //return edited text to the main activity
        edbutton.setOnClickListener(View.OnClickListener {
            Log.i("Ed clicked", "Edit button has been clicked")

            val intent = Intent(this, MainActivity::class.java)
            //convert the edited text from a bitmap or char array to string
            intent.putExtra("keytext", edit.text.toString())
            //pass the edited text
            getIntent().extras?.let { it1 -> intent.putExtra("position", it1.getInt("position")) }
            setResult(Activity.RESULT_OK, intent)
            //end the second activity
            finish()
        })




    }



    }



