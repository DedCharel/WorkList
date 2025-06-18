package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.work_list.WorkListRepository
import javax.inject.Inject

class AddOrganizationUseCase @Inject constructor(
    private val repository: OrganizationRepository
) {
    suspend fun addOrganization(organization: Organization){
        repository.addOrganization(organization)
    }
}