package com.example.simpletodo

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * A knockoff bridge that scripts the recycler how to
 * display the PASSED data we provide with it by Intent
 */
class EditTaskAdapter (val editTask: List<String>):

    RecyclerView.Adapter<EditTaskAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)
        }

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val context = parent.context

            val inflater = LayoutInflater.from(context)
            // Inflate the custom layout
            val contactView = inflater.inflate(R.layout.simple_list_item_1, parent, false)
            // Return a new holder instance
            return ViewHolder(contactView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = editTask.get(position)

            holder.textView.text = item
        }

        override fun getItemCount(): Int {
            return editTask.size
        }
    }


