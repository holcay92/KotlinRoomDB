package com.example.roomdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.db.Student
import com.example.roomdatabasedemo.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var viewModel: StudentViewModel
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter
    private var isListItemSelected = false

    private lateinit var selectedStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        studentRecyclerView = findViewById(R.id.rvItem)


        // dao instance is created
        val dao = StudentDatabase.getInstance(application).studentDao()
        // view model instance is created
        val factory = StudentViewModelFactory(dao)
        // initializing the view model
        viewModel = factory.create(StudentViewModel::class.java)

        saveButton.setOnClickListener {
            if (isListItemSelected) {
               updateStudentData()
                clearInput()
            } else {
                saveStudentData()
                clearInput()
            }
        }
        clearButton.setOnClickListener {
            if (isListItemSelected) {
                deleteStudentData()
                clearInput()
            } else {
                clearInput()
            }
        }

        initRecyclerView()

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

    private fun updateStudentData() {
        viewModel.updateStudent(
            Student(
                selectedStudent.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemSelected = false
    }

    private fun deleteStudentData() {
        viewModel.deleteStudent(
            Student(
                selectedStudent.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemSelected = false
    }

    private fun clearInput() {
        nameEditText.setText("")
        emailEditText.setText("")
    }

    private fun initRecyclerView() {
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: Student ->
            listItemClicked(selectedItem)
        }
        studentRecyclerView.adapter = adapter
        displayStudentList()

    }

    private fun displayStudentList() {
        viewModel.students.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })

    }

    private fun listItemClicked(student: Student) {
/*
       Toast.makeText(this,"${student.name} is clicked",Toast.LENGTH_SHORT).show()
*/
        selectedStudent = student
        saveButton.text = "Update"
        clearButton.text = "Delete"
        isListItemSelected = true
        nameEditText.setText(selectedStudent.name)
        emailEditText.setText(selectedStudent.email)

    }
}
