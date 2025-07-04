package ru.nvgsoft.worklist.presentation.work

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.domain.work_list.WorkItem
import ru.nvgsoft.worklist.utils.convertLongToDate

class WorkListAdapter: ListAdapter<WorkItem, WorkItemViewHolder>(WorkItemDiffCallback()){

    var onWorkItemClickListener: ((workItem: WorkItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return WorkItemViewHolder(view)
    }



    override fun onBindViewHolder(holder: WorkItemViewHolder, position: Int) {
        val workItem = getItem(position)
        with(holder){
            tvDate.text = convertLongToDate( workItem.date)
            tvOrganisation.text = workItem.organisation
            tvWorker.text = workItem.worker
            tvSpendTime.text = workItem.spendTime.toString()
        }
        holder.view.setOnClickListener { onWorkItemClickListener?.invoke(workItem) }
    }


}