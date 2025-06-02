package ru.nvgsoft.worklist.domain.work_list

import androidx.lifecycle.LiveData
import ru.nvgsoft.worklist.domain.WorkListRepository
import javax.inject.Inject

class GetWorkLIstUseCase @Inject constructor(private val repository: WorkListRepository) {

    fun getWorkList(): LiveData<List<WorkItem>>{
        return repository.getWorkList()
    }
}