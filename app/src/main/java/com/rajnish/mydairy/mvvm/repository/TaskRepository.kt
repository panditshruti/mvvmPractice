package com.rajnish.mydairy.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rajnish.mydairy.firebase.TaskFirebase
import com.rajnish.mydairy.room.Task
import com.rajnish.mydairy.room.TaskDao
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao){

    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Details")
    private val _tasksLiveData = MutableLiveData<List<TaskFirebase>>()
    val tasksLiveData: LiveData<List<TaskFirebase>> = _tasksLiveData

    suspend fun details(task: Task){
        taskDao.insertTask(task)

    }
     fun getAllTask():LiveData<List<Task>>{
       return  taskDao.getAllTask()

    }
    suspend fun deleteTask(deleteTask:String){
        taskDao.deleteById(deleteTask)

    }


    fun addTaskToFirebase(taskFirebase: TaskFirebase)
    {
        databaseReference.push().setValue(taskFirebase)
    }


    fun retrieveTasksFromFirebase() {
        val databaseReference = FirebaseDatabase.getInstance().reference.child("Details")

        val taskListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tasks = mutableListOf<TaskFirebase>()
                for (taskSnapshot in dataSnapshot.children) {
                    val title = taskSnapshot.child("title").getValue(String::class.java)
                    val desc = taskSnapshot.child("desc").getValue(String::class.java)
                    if (title != null && desc != null) {
                        val task = TaskFirebase(title, desc)
                        tasks.add(task)
                    }
                }
                _tasksLiveData.value = tasks
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
            }
        }

        databaseReference.addListenerForSingleValueEvent(taskListener)
    }


}

