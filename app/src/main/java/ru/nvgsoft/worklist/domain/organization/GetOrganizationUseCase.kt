package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.work_list.WorkListRepository
import javax.inject.Inject

class GetOrganizationUseCase @Inject constructor(
    private val repository: OrganizationRepository
) {

    suspend fun getOrganization(organizationId: Int): Organization {
        return repository.getOrganization(organizationId)
    }
}