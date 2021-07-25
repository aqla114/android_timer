package com.example.timer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timer.entities.Task
import com.example.timer.formatMillisecToTime

class CompletedTasksListAdapter(private val tasks: List<Task>): RecyclerView.Adapter<CompletedTasksListAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(cell: View): RecyclerView.ViewHolder(cell) {
        val text1: TextView = cell.findViewById(android.R.id.text1)
        val text2: TextView = cell.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.text1.text = task?.taskName
        holder.text2.text = "評価：%d　時間：%d分".format(task?.evaluation, task?.time)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    private fun getItem(position: Int): Task? {
        return tasks[position]
    }
}