package ru.nvgsoft.worklist.presentation.organization

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nvgsoft.worklist.R

class OrganizationViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val tvOrganization = view.findViewById<TextView>(R.id.tv_organization)
}