package com.example.roomdatabasedemo.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface StudentDao {
    // this will insert the student object into the database
    // we have to use insert annotation
    // to tell room that this is an insert method
    @Insert
    // suspend means that this function will be called from a coroutine
    suspend fun insertStudent(student: Student)
}