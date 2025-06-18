package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.work_list.WorkListRepository
import javax.inject.Inject

class DeleteOrganizationUseCase @Inject constructor(
    private val repository: OrganizationRepository
) {

    suspend fun deleteOrganization(organizationId: Int) {
        repository.deleteOrganization(organizationId)
    }
}