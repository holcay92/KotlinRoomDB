package com.example.roomdatabasedemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.databinding.ActivityMainBinding
import com.example.roomdatabasedemo.db.Student
import com.example.roomdatabasedemo.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    // we have to rebuild the project after adding the binding feature to the project dependencies!!
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentRecyclerViewAdapter
    private var isListItemSelected = false

    private lateinit var selectedStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        bindingMain


        // dao instance is created
        val dao = StudentDatabase.getInstance(application).studentDao()
        // view model instance is created
        val factory = StudentViewModelFactory(dao)
        // initializing the view model
        viewModel = factory.create(StudentViewModel::class.java)

        // apply is used for both buttons to avoid repeating the same code
        bindingMain.apply {
            btnSave.setOnClickListener {
                if (isListItemSelected) {
                    updateStudentData()
                    clearInput()
                } else {
                    saveStudentData()
                    clearInput()
                }
            }

            btnClear.setOnClickListener {
                if (isListItemSelected) {
                    deleteStudentData()
                    clearInput()
                } else {
                    clearInput()
                }
            }
            initRecyclerView()
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
        bindingMain.apply {
            viewModel.insertStudent(
                Student(
                    0,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }

    }

    private fun updateStudentData() {

        bindingMain.apply {
            viewModel.updateStudent(
                Student(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemSelected = false
        }

    }

    private fun deleteStudentData() {

        bindingMain.apply {
            viewModel.deleteStudent(
                Student(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemSelected = false
        }

    }

    private fun clearInput() {
        bindingMain.apply {
            etName.setText("")
            etEmail.setText("")
        }

    }

    private fun initRecyclerView() {
        bindingMain.apply {
            rvItem.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = StudentRecyclerViewAdapter { selectedItem: Student ->
                listItemClicked(selectedItem)
            }
            rvItem.adapter = adapter
            displayStudentList()
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayStudentList() {
        viewModel.students.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }

    }

    private fun listItemClicked(student: Student) {
/*
       Toast.makeText(this,"${student.name} is clicked",Toast.LENGTH_SHORT).show()
*/
        bindingMain.apply{
            selectedStudent = student
            btnSave.text = "Update"
            btnClear.text = "Delete"
            isListItemSelected = true
            etName.setText(selectedStudent.name)
            etEmail.setText(selectedStudent.email)
        }
    }
}
