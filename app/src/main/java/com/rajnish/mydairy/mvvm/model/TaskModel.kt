package com.rajnish.mydairy.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajnish.mydairy.firebase.TaskFirebase
import com.rajnish.mydairy.mvvm.repository.TaskRepository
import com.rajnish.mydairy.room.Task
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskModel @Inject constructor(private val repository: TaskRepository):ViewModel(){

    fun insertTask(task: Task){
        viewModelScope.launch {
            repository.details(task)
        }
    }
    fun getAllTask() : LiveData<List<Task>> {
        return  repository.getAllTask()

    }
    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }

    fun addTaskToFirebase(taskFirebase: TaskFirebase){
        repository.addTaskToFirebase(taskFirebase)
    }

    }