package ru.nvgsoft.worklist.domain

class GetWorkLIstUseCase(private val repository: WorkListRepository) {

    fun getWorkList(): List<WorkItem>{
        return repository.getWorkList()
    }
}