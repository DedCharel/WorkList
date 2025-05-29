package ru.nvgsoft.worklist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nvgsoft.worklist.domain.DeleteWorkItemUSeCase
import ru.nvgsoft.worklist.domain.GetWorkLIstUseCase
import ru.nvgsoft.worklist.domain.WorkItem
import javax.inject.Inject

class WorkListViewModel @Inject constructor(
    private val getWorkListUseCase: GetWorkLIstUseCase,
    private val deleteWorkItemUSeCase: DeleteWorkItemUSeCase
    ) : ViewModel() {





    val workList = getWorkListUseCase.getWorkList()

    fun deleteWorkItem(workItem: WorkItem) {
        viewModelScope.launch {
            deleteWorkItemUSeCase.deleteWorkItem(workItem)
        }

    }


}