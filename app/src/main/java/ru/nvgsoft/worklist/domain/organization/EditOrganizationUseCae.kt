package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class EditOrganizationUseCae @Inject constructor(private val repository: WorkListRepository) {

    suspend fun editOrganization(organization: Organization){
        repository.editOrganization(organization)
    }
}