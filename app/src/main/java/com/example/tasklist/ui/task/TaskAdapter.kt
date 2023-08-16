package com.example.tasklist.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.R
import com.example.tasklist.model.Task

class TaskAdapter(
    var list: ArrayList<Task>,
    private val onLongClickTask: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder{
        val itemTask = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemTask)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TaskViewHolder(itemTask: View) : RecyclerView.ViewHolder(itemTask) {
        init {
            val taskCheckbox = itemTask.findViewById<CheckBox>(R.id.radio_btn)

            itemTask.setOnLongClickListener {
                onLongClickTask(list[adapterPosition])
                true
            }

            taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
                val task = list[adapterPosition]
                task.isCompleted = isChecked
            }
        }

        fun bind(task: Task) {
            val taskTitleTextView = itemView.findViewById<TextView>(R.id.tv_title)
            val taskDescriptionTextView = itemView.findViewById<TextView>(R.id.tv_desc)
            val taskCheckbox = itemView.findViewById<CheckBox>(R.id.radio_btn)

            taskTitleTextView.text = task.title
            taskDescriptionTextView.text = task.description
            taskCheckbox.isChecked = task.isCompleted
        }
    }
}