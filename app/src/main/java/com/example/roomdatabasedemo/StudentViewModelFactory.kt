package com.example.roomdatabasedemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasedemo.db.StudentDao

class StudentViewModelFactory(private val dao: StudentDao):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)){
            Log.d("StudentViewModelFactory", "create: ")
            return StudentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

