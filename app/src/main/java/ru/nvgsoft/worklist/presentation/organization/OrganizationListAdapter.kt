package ru.nvgsoft.worklist.presentation.organization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.domain.organization.Organization

class OrganizationListAdapter : ListAdapter<Organization, OrganizationViewHolder>(OrganizationItemDiffCallback()) {

    var onOrganizationItemClick: ((Organization) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_organization, parent, false)
        return OrganizationViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        val organization =  getItem(position)
        with (holder){
            tvOrganization.text = organization.name
        }
        holder.view.setOnClickListener {  onOrganizationItemClick?.invoke(organization) }
    }
}