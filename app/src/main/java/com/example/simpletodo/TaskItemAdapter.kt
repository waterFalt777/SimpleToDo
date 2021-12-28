package com.example.simpletodo

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * A bridge that scripts the recycler how to
 * display the data we provide with it
 */
class TaskItemAdapter(val listOfItems: List<String>,val longClickListener: OnLongClickListener, val ClickListener: OnClickListener):



    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {



    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
        }

    interface OnClickListener{
        fun onItemClicked(position: Int)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context

        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Get the data model based on position
        val item = listOfItems.get(position)

        holder.textView.text = item
    }

     override fun getItemCount(): Int {
        return listOfItems.size
    }

    //Provide a direct reference to each of the view w/n a data item
    //Used to cache views w/n the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Store references to elements in our layout view
        val textView: TextView

        init{
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener{
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }


            itemView.setOnClickListener{
                ClickListener.onItemClicked(adapterPosition)
                true
            }
        }


    }

}