package com.rajnish.mydairy.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    suspend fun  insertTask(task: Task) /// save

    @Query("SELECT * FROM Task")
     fun getAllTask():LiveData<List<Task>>


    @Query("DELETE FROM Task WHERE id = :taskId")
    suspend fun deleteById(taskId: Long)

 @Query("SELECT title FROM Task WHERE date = :dateToday")
    fun getTitle(dateToday:String):LiveData<String>

    @Query("SELECT title FROM Task WHERE date BETWEEN :startDate AND :endDate")
    fun getTitleBetweenGivenDate(startDate:String,endDate:String):LiveData<List<String>>



    




}