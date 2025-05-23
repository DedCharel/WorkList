package ru.nvgsoft.worklist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nvgsoft.worklist.R

class WorkItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvSpendTime = view.findViewById<TextView>(R.id.tv_spend_time)
    val tvOrganisation = view.findViewById<TextView>(R.id.tv_organisation)
    val tvWorker = view.findViewById<TextView>(R.id.tv_worker)
    val tvDate = view.findViewById<TextView>(R.id.tv_date)
}