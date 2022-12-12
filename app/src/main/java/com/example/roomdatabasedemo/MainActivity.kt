package com.example.roomdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.roomdatabasedemo.db.Student
import com.example.roomdatabasedemo.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var viewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)

        // dao instance is created
        val dao = StudentDatabase.getInstance(application).studentDao()
        // view model instance is created
        val factory = StudentViewModelFactory(dao)
        // initializing the view model
        viewModel = factory.create(StudentViewModel::class.java)

        saveButton.setOnClickListener {
         saveStudentData()
            clearInput()
        }


    }

    private fun saveStudentData() {
        /*  val name = nameEditText.text.toString()
          val email = emailEditText.text.toString()
          // we set the id to 0 because we set the id
          // auto generate by defining it in the entity Student
          val student = Student(0, name,email)
          viewModel.insertStudent(student)*/

        //this is the same as above
        viewModel.insertStudent(
            Student(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
    }

    private fun clearInput() {
        nameEditText.setText("")
        emailEditText.setText("")
    }
}