package com.example.tasklist.ui.main

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.tasklist.databinding.ActivityMainBinding
import com.example.tasklist.model.Task
import com.example.tasklist.CreateTaskActivity
import com.example.tasklist.ui.task.TaskAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = TaskAdapter(arrayListOf(), this::onLongClickTask)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.notifyDataSetChanged()
        initView()
        initClick()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        binding.recyclerView.adapter = adapter

        viewModel.list.observe(this) { updatedList ->
            adapter.list = updatedList
            adapter.notifyDataSetChanged()
        }
    }

    private fun initClick() {
        binding.fab.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onLongClickTask(task: Task) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Delete Task")
            .setMessage("Are you sure you want to delete this task?")
            .setPositiveButton("ОК") { dialog: DialogInterface, _: Int ->
                viewModel.deleteTask(task)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
        dialogBuilder.show()
    }
}