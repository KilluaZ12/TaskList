package com.example.tasklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.tasklist.databinding.ActivityCreateTaskBinding
import com.example.tasklist.model.Task
import com.example.tasklist.ui.main.MainActivity
import com.example.tasklist.ui.main.MainViewModel

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTaskBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClick()
    }

    private fun initClick() {
        binding.btnCreate.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
            viewModel.setTask(data)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}