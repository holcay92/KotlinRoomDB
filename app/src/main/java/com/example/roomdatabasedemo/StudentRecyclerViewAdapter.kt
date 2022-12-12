package com.example.roomdatabasedemo

import android.view.LayoutInflater
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.db.Student

class StudentRecyclerViewAdapter(private val clickListener: (Student) -> Unit) :RecyclerView.Adapter<StudentViewHolder>(){

    private val studentList= arrayListOf<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //layout object is created
       val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return StudentViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
       return holder.bind(studentList[position],clickListener)
    }

    override fun getItemCount(): Int {
        //return the size of the list
        return studentList.size
    }

    fun setList(list:List<Student>){
        //thats because we want to update the list
        studentList.clear()
        studentList.addAll(list)
    }

}
