package ru.nvgsoft.worklist.domain

import androidx.lifecycle.LiveData

class GetWorkLIstUseCase(private val repository: WorkListRepository) {

    fun getWorkList(): LiveData<List<WorkItem>>{
        return repository.getWorkList()
    }
}