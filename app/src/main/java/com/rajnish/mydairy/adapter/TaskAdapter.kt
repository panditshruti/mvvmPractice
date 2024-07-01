package com.rajnish.mydairy.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.interfaceForListner.ItemDismissListener
import com.rajnish.mydairy.room.Task

class TaskAdapter(private val data: List<Task>, private val listener: ItemDismissListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.taskTitle)
        val taskDescription: TextView = itemView.findViewById(R.id.taskDescription)
        val taskCreatedTime: TextView = itemView.findViewById(R.id.taskCreatedTime)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = data[position]
        holder.taskTitle.text = task.title
        holder.taskDescription.text = task.description
        holder.taskCreatedTime.text = task.date


        holder.deleteButton.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                listener.onItemDismiss(task.id)
            }

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
