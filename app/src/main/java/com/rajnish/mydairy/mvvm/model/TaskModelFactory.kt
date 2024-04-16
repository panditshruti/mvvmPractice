package com.rajnish.mydairy.mvvm.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rajnish.mydairy.mvvm.repository.TaskRepository


@Suppress("UNCHECKED_CAST")
class TaskModelFactory(private val repository: TaskRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskModel::class.java)){

            return TaskModel(repository) as T
        }


       throw IllegalArgumentException("Unknown ViewModel class")

    }
}