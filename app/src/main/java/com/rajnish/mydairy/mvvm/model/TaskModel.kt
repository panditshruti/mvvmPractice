package com.rajnish.mydairy.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    fun deleteTask(taskId: Long) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }
      fun getTitle(dateToday:String):LiveData<String> {
            return   repository.getTitle(dateToday)
    }

    fun getTitleBetweenGivenDate(startDate:String,endDate:String):LiveData<List<String>>{
        return repository.getTitleBetweenGivenDate(startDate,endDate)
    }



    }