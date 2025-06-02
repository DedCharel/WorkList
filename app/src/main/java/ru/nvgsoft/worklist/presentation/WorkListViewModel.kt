package ru.nvgsoft.worklist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nvgsoft.worklist.domain.work_list.DeleteWorkItemUSeCase
import ru.nvgsoft.worklist.domain.work_list.GetWorkLIstUseCase
import ru.nvgsoft.worklist.domain.work_list.WorkItem
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