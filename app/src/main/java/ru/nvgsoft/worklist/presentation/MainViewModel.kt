package ru.nvgsoft.worklist.presentation

import androidx.lifecycle.ViewModel
import ru.nvgsoft.worklist.data.WorkListRepositoryImpl
import ru.nvgsoft.worklist.domain.DeleteWorkItemUSeCase
import ru.nvgsoft.worklist.domain.GetWorkLIstUseCase
import ru.nvgsoft.worklist.domain.WorkItem

class MainViewModel : ViewModel() {

    private val repository = WorkListRepositoryImpl() //temp

    private val getWorkListUseCase = GetWorkLIstUseCase(repository)
    private val deleteWorkItemUSeCase = DeleteWorkItemUSeCase(repository)

    val workList = getWorkListUseCase.getWorkList()

    fun deleteWorkItem(workItem: WorkItem) {
        deleteWorkItemUSeCase.deleteWorkItem(workItem)
    }


}