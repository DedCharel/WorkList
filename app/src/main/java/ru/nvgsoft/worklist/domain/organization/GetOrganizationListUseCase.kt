package ru.nvgsoft.worklist.domain.organization

import androidx.lifecycle.LiveData
import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class GetOrganizationListUseCase @Inject constructor(private val repository: WorkListRepository) {

    fun getOrganizationList(): LiveData<List<Organization>>{
        return repository.getOrganizationList()
    }

}