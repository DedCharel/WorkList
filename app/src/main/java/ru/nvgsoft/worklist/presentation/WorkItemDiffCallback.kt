package ru.nvgsoft.worklist.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.nvgsoft.worklist.domain.work_list.WorkItem

class WorkItemDiffCallback: DiffUtil.ItemCallback<WorkItem>() {
    override fun areItemsTheSame(oldItem: WorkItem, newItem: WorkItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WorkItem, newItem: WorkItem): Boolean {
        return oldItem == newItem
    }
}