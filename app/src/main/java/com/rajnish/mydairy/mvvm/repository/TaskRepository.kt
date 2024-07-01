package com.rajnish.mydairy.mvvm.repository

import androidx.lifecycle.LiveData
import com.rajnish.mydairy.room.Task
import com.rajnish.mydairy.room.TaskDao
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {


    suspend fun details(task: Task) {
        taskDao.insertTask(task)

    }

    fun getAllTask(): LiveData<List<Task>> {
        return taskDao.getAllTask()

    }

    suspend fun deleteTask(deleteTask: Long) {
        taskDao.deleteById(deleteTask)
    }

 fun getTitle(dateToday:String):LiveData<String>{
    return taskDao.getTitle(dateToday)
}

    fun getTitleBetweenGivenDate(startDate:String,endDate:String):LiveData<List<String>>{

        return taskDao.getTitleBetweenGivenDate(startDate,endDate)
    }



}

