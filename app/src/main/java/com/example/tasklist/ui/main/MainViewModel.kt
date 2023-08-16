package com.example.tasklist.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasklist.model.Task

class MainViewModel : ViewModel() {

    private val arrayList = ArrayList<Task>()
    private val _list = MutableLiveData<ArrayList<Task>>()
    val list: LiveData<ArrayList<Task>> = _list

    init {
        _list.value = arrayList
    }

    fun setTask(task: Task) {
        Log.d("ViewModel", "Adding task: ${task.title}")
        arrayList.add(task)
        _list.value = arrayList
    }

    fun markTaskCompleted(task: Task) {
        task.isCompleted = true
        _list.value = arrayList
    }

    fun deleteTask(task: Task) {
        arrayList.removeIf {
            it.title == task.title
        }
        _list.value = arrayList
    }
}