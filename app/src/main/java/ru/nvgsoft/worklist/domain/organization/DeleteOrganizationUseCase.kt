package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class DeleteOrganizationUseCase @Inject constructor(private val repository: WorkListRepository) {

    suspend fun deleteOrganization(organizationId: Int) {
        repository.deleteOrganization(organizationId)
    }
}