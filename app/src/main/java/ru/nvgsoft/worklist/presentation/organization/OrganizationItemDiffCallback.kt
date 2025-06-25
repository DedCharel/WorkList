package ru.nvgsoft.worklist.presentation.organization

import androidx.recyclerview.widget.DiffUtil
import ru.nvgsoft.worklist.domain.organization.Organization

class OrganizationItemDiffCallback: DiffUtil.ItemCallback<Organization>() {
    override fun areItemsTheSame(oldItem: Organization, newItem: Organization): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Organization, newItem: Organization): Boolean {
        return oldItem == newItem
    }
}