package com.example.roomdatabasedemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    // this will insert the student object into the database
    // we have to use insert annotation
    // to tell room that this is an insert method
    @Insert
    // suspend means that this function will be called from a coroutine
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)


    @Query("SELECT * FROM student_data_table")
    // we have to use sql query for this
    fun getAllStudents(): LiveData<List<Student>>
}