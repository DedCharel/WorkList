package ru.nvgsoft.worklist.domain.organization

import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class AddOrganizationUseCase @Inject constructor(
    private val repository: WorkListRepository
) {
    suspend fun addOrganization(organization: Organization){
        repository.addOrganization(organization)
    }
}