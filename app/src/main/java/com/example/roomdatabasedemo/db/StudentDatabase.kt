package com.example.roomdatabasedemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// exportSchema = false means that
// we don't want to export the schema to a json file
// at runtime
@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    // singleton pattern
    companion object {
        // @Volatile means that
        // this variable will be visible to all threads immediately
        @Volatile
        private var INSTANCE: StudentDatabase? = null
        fun getInstance(context: Context): StudentDatabase {

            synchronized(this) {
                var instance = INSTANCE
                // if the instance is null then create a new database
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_database"
                    ).build()
                }
                return instance
            }
        }
    }
}


