package ru.nvgsoft.worklist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.domain.WorkItem

class WorkListAdapter: ListAdapter<WorkItem, WorkItemViewHolder>(WorkItemDiffCallback()){

    var onWorkItemClickListener: ((workItem: WorkItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return WorkItemViewHolder(view)
    }



    override fun onBindViewHolder(holder: WorkItemViewHolder, position: Int) {
        val workItem = getItem(position)
        with(holder){
            tvDate.text = workItem.date.toString()
            tvOrganisation.text = workItem.organisation
            tvWorker.text = workItem.worker
            tvSpendTime.text = workItem.spendTime.toString()
        }
        holder.view.setOnClickListener { onWorkItemClickListener?.invoke(workItem) }
    }


}