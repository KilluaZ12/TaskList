package com.example.tasklist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasklist.model.Task

class MainViewModel : ViewModel() {

    private var arrayList = ArrayList<Task>()
    private var _list = MutableLiveData(arrayList)
    val list: LiveData<ArrayList<Task>> = _list

    fun setTask(task: Task) {
        arrayList.add(task)
    }

    fun markTaskCompleted(task: Task) {
        task.isCompleted = true
    }

    fun deleteTask(task: Task) {
        arrayList.removeIf {
            it.title == task.title
        }
    }

    fun getAllTasks(): LiveData<ArrayList<Task>> {
        return list
    }
}