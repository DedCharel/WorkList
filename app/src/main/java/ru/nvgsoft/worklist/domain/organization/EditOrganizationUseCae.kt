package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.work_list.WorkListRepository
import javax.inject.Inject

class EditOrganizationUseCae @Inject constructor(
    private val repository: OrganizationRepository
) {

    suspend fun editOrganization(organization: Organization){
        repository.editOrganization(organization)
    }
}