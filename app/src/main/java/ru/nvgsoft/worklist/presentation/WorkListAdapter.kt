package ru.nvgsoft.worklist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.domain.WorkItem

class WorkListAdapter: RecyclerView.Adapter<WorkListAdapter.WorkListViewHolder>() {

    var workList = listOf<WorkItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    var onWorkItemClickListener: ((workItem: WorkItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return WorkListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workList.size
    }

    override fun onBindViewHolder(holder: WorkListViewHolder, position: Int) {
        val workItem = workList[position]
        with(holder){
            tvDate.text = workItem.date.toString()
            tvOrganisation.text = workItem.organisation
            tvWorker.text = workItem.worker
            tvSpendTime.text = workItem.spendTime.toString()
        }
        holder.view.setOnClickListener { onWorkItemClickListener?.invoke(workItem) }
    }

    class WorkListViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvSpendTime = view.findViewById<TextView>(R.id.tv_spend_time)
        val tvOrganisation = view.findViewById<TextView>(R.id.tv_organisation)
        val tvWorker = view.findViewById<TextView>(R.id.tv_worker)
        val tvDate = view.findViewById<TextView>(R.id.tv_date)

    }
}