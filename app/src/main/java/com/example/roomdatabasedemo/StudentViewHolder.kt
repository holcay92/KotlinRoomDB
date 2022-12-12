package com.example.roomdatabasedemo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.db.Student

class StudentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(student: Student,clickListener: (Student) -> Unit) {
        val name = view.findViewById<TextView>(R.id.tvName)
        val email = view.findViewById<TextView>(R.id.tvEmail)
        name.text = student.name
        email.text = student.email
        view.setOnClickListener{
            clickListener(student)
        }
    }
}
