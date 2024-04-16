package com.rajnish.mydairy.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.appwrite.ID
@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title :String,
    var description: String,
    var date : String
)
