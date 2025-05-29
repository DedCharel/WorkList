package ru.nvgsoft.worklist.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetWorkLIstUseCase @Inject constructor(private val repository: WorkListRepository) {

    fun getWorkList(): LiveData<List<WorkItem>>{
        return repository.getWorkList()
    }
}