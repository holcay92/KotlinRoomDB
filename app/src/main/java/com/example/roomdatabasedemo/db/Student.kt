package com.example.roomdatabasedemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// room understands that this is a table
@Entity(tableName = "student_data_table")
data class Student(
    // room understands that the id is the primary key
    @PrimaryKey(autoGenerate = true)
    //if we want to change the name of the column in the table
    @ColumnInfo(name = "student_id")
    val id: Int,
    @ColumnInfo(name = "student_name")
    val name: String,
    @ColumnInfo(name = "student_email")
    val email: String
)
