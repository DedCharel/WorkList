package ru.nvgsoft.worklist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nvgsoft.worklist.data.WorkListRepositoryImpl
import ru.nvgsoft.worklist.domain.DeleteWorkItemUSeCase
import ru.nvgsoft.worklist.domain.GetWorkLIstUseCase
import ru.nvgsoft.worklist.domain.WorkItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WorkListRepositoryImpl(application) //temp

    private val getWorkListUseCase = GetWorkLIstUseCase(repository)
    private val deleteWorkItemUSeCase = DeleteWorkItemUSeCase(repository)

    val workList = getWorkListUseCase.getWorkList()

    fun deleteWorkItem(workItem: WorkItem) {
        viewModelScope.launch {
            deleteWorkItemUSeCase.deleteWorkItem(workItem)
        }

    }


}