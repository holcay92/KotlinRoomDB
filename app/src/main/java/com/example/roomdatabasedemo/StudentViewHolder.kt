package com.example.roomdatabasedemo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.databinding.ListItemBinding
import com.example.roomdatabasedemo.db.Student

class StudentViewHolder(private val bindingItem : ListItemBinding) : RecyclerView.ViewHolder(bindingItem.root) {
    fun bind(student: Student,clickListener: (Student) -> Unit) {

        bindingItem.apply {

            tvName.text = student.name
            tvEmail.text = student.email
            root.setOnClickListener{
                clickListener(student)
            }
        }
    }
}
