package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class GetOrganizationUseCase @Inject constructor(private val repository: WorkListRepository) {

    suspend fun getOrganization(organizationId: Int): Organization {
        return repository.getOrganization(organizationId)
    }
}