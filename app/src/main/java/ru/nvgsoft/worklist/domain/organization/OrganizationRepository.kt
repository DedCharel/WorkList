package ru.nvgsoft.worklist.domain.organization

import androidx.lifecycle.LiveData

interface OrganizationRepository {

    fun getOrganizationList(): LiveData<List<Organization>>

    suspend fun deleteOrganization(organizationId: Int)

    suspend fun getOrganization(organizationId: Int): Organization

    suspend fun addOrganization(organization: Organization)

    suspend fun editOrganization(organization: Organization)
}
