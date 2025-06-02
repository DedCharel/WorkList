package ru.nvgsoft.worklist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.nvgsoft.worklist.domain.organization.Organization
import ru.nvgsoft.worklist.domain.work_list.WorkItem
import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class WorkListRepositoryImpl @Inject constructor(
    private val workListDao: WorkListDao,
    private val mapper:Mapper
    ) : WorkListRepository {


    override fun getWorkList(): LiveData<List<WorkItem>> {
        return workListDao.getWorkList().map { mapper.mapListDbModelToListEntity(it) }
    }


    override suspend fun getWorkItem(itemId: Int): WorkItem {
        val workItemDbModel = workListDao.getWorkItem(itemId)
        return mapper.mapDbModelToEntity(workItemDbModel)

    }

    override suspend fun deleteWorkItem(workItem: WorkItem) {
        workListDao.deleteWorkItem(workItem.id)
    }

    override suspend fun editWorkItem(workItem: WorkItem) {
        workListDao.addWorkItem(mapper.mapEntityToDbModel(workItem))
    }

    override suspend fun addWorkItem(workItem: WorkItem) {
       workListDao.addWorkItem(mapper.mapEntityToDbModel(workItem))
    }

    //Organization

    override fun getOrganizationList(): LiveData<List<Organization>> {
        return workListDao.getOrganizationList().map { mapper.mapListDbModelOrganizationToListEntity(it) }
    }

    override suspend fun deleteOrganization(organizationId: Int) {
        workListDao.deleteOrganization(organizationId)
    }

    override suspend fun getOrganization(organizationId: Int): Organization {
       val organizationDbModel  = workListDao.getOrganization(organizationId)
        return mapper.mapDbModelToEntity(organizationDbModel)
    }

    override suspend fun addOrganization(organization: Organization) {
        workListDao.addOrganization(mapper.mapEntityToDbModel(organization))
    }

    override suspend fun editOrganization(organization: Organization) {
        workListDao.addOrganization(mapper.mapEntityToDbModel(organization))
    }

}