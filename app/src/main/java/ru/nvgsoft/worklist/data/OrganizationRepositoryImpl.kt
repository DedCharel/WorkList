package ru.nvgsoft.worklist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.nvgsoft.worklist.domain.organization.Organization
import ru.nvgsoft.worklist.domain.organization.OrganizationRepository
import javax.inject.Inject

class OrganizationRepositoryImpl @Inject constructor(
    private val organizationDao: OrganizationDao,
    private val mapper:Mapper
): OrganizationRepository {

    //Organization

    override fun getOrganizationList(): LiveData<List<Organization>> {
        return organizationDao.getOrganizationList().map { mapper.mapListDbModelOrganizationToListEntity(it) }
    }

    override suspend fun deleteOrganization(organizationId: Int) {
        organizationDao.deleteOrganization(organizationId)
    }

    override suspend fun getOrganization(organizationId: Int): Organization {
        val organizationDbModel  = organizationDao.getOrganization(organizationId)
        return mapper.mapDbModelToEntity(organizationDbModel)
    }

    override suspend fun addOrganization(organization: Organization) {
        organizationDao.addOrganization(mapper.mapEntityToDbModel(organization))
    }

    override suspend fun editOrganization(organization: Organization) {
        organizationDao.addOrganization(mapper.mapEntityToDbModel(organization))
    }
}