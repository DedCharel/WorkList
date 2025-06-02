package ru.nvgsoft.worklist.domain

import androidx.lifecycle.LiveData
import ru.nvgsoft.worklist.domain.organization.Organization
import ru.nvgsoft.worklist.domain.work_list.WorkItem

interface WorkListRepository {

    fun getWorkList(): LiveData<List<WorkItem>>

    suspend fun getWorkItem(itemId: Int): WorkItem

    suspend fun deleteWorkItem(workItem: WorkItem)

    suspend fun editWorkItem(workItem: WorkItem)

    suspend fun addWorkItem(workItem: WorkItem)




    fun getOrganizationList(): LiveData<List<Organization>>

    suspend fun deleteOrganization(organizationId: Int)

    suspend fun getOrganization(organizationId: Int): Organization

    suspend fun addOrganization(organization: Organization)

    suspend fun editOrganization(organization: Organization)
}