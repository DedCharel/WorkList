package ru.nvgsoft.worklist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nvgsoft.worklist.domain.work_list.AddWorkItemUseCase
import ru.nvgsoft.worklist.domain.work_list.EditWorkItemUSeCase
import ru.nvgsoft.worklist.domain.work_list.GetWorkItemUseCase
import ru.nvgsoft.worklist.domain.work_list.WorkItem
import ru.nvgsoft.worklist.utils.convertDateToLong
import javax.inject.Inject

class WorkItemViewModel @Inject constructor(
    private val addWorkItemUseCase: AddWorkItemUseCase,
    private val editWorkItemUSeCase: EditWorkItemUSeCase,
    private val getWorkItemUseCase: GetWorkItemUseCase
) : ViewModel() {


    private val _workItem = MutableLiveData<WorkItem>()
    val workItem: LiveData<WorkItem>
        get() = _workItem

    private val _errorInputWorker = MutableLiveData<Boolean>()
    val errorInputWorker: LiveData<Boolean>
        get() = _errorInputWorker

    private val _errorInputOrganisation = MutableLiveData<Boolean>()
    val errorInputOrganisation: LiveData<Boolean>
        get() = _errorInputOrganisation

    private val _errorInputSpendTime = MutableLiveData<Boolean>()
    val errorInputSpendTime: LiveData<Boolean>
        get() = _errorInputSpendTime

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun addWorkItem(
        date: String?,
        worker: String?,
        organisation: String?,
        description: String?,
        spendTime: String?
    ) {
        val newDate = parseDate(date)
        val newWorker = worker ?: ""
        val newOrganisation = organisation ?: ""
        val newDescription = description ?: ""
        val newSpendTime = parseSpendTime(spendTime)
        if (validateInputData(newDate, newWorker, newOrganisation, newSpendTime)) {
            viewModelScope.launch {
                addWorkItemUseCase.addWorkItem(
                    WorkItem(
                        date = newDate,
                        worker = newWorker,
                        organisation = newOrganisation,
                        description = newDescription,
                        spendTime = newSpendTime
                    )
                )
                _shouldCloseScreen.value = Unit
            }
        }
    }

    fun editWorkItem(
        date: String?,
        worker: String?,
        organisation: String?,
        description: String?,
        spendTime: String?
    ) {
        val newDate = parseDate(date)
        val newWorker = worker ?: ""
        val newOrganisation = organisation ?: ""
        val newDescription = description ?: ""
        val newSpendTime = parseSpendTime(spendTime)
        if (validateInputData(newDate, newWorker, newOrganisation, newSpendTime)) {
            _workItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(
                        date = newDate,
                        worker = newWorker,
                        organisation = newOrganisation,
                        description = newDescription,
                        spendTime = newSpendTime
                    )
                    editWorkItemUSeCase.editWorkItem(item)
                    _shouldCloseScreen.value = Unit
                }
            }
        }
    }

    fun getWorkItem(itemId: Int) {
        viewModelScope.launch {
            val item = getWorkItemUseCase.getWorkItem(itemId)
            _workItem.value = item
        }
    }


    fun resetErrorInputWorker() {
        _errorInputWorker.value = false
    }

    fun resetErrorInputOrganisation() {
        _errorInputOrganisation.value = false
    }

    fun resetErrorInputSpendTime() {
        _errorInputSpendTime.value = false
    }


    private fun parseDate(date: String?): Long {
        return try {
            val dateString = date ?: ""
            convertDateToLong(dateString)
        } catch (e: Exception) {
            0
        }
    }

    private fun parseSpendTime(spendTime: String?): Double {
        return try {
            spendTime?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun validateInputData(
        date: Long,
        worker: String,
        organisation: String,
        spendTime: Double
    ): Boolean {
        var result = true
        if (worker.isBlank()) {
            _errorInputWorker.value = true
            result = false
        }
        if (organisation.isBlank()) {
            _errorInputOrganisation.value = true
            result = false
        }
        if (spendTime <= 0) {
            _errorInputSpendTime.value = true
            result = false
        }
        return result
    }


}