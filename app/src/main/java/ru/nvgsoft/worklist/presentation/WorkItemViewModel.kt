package ru.nvgsoft.worklist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nvgsoft.worklist.data.WorkListRepositoryImpl
import ru.nvgsoft.worklist.domain.AddWorkItemUseCase
import ru.nvgsoft.worklist.domain.EditWorkItemUSeCase
import ru.nvgsoft.worklist.domain.GetWorkItemUseCase
import ru.nvgsoft.worklist.domain.WorkItem
import ru.nvgsoft.worklist.utils.convertDateToLong

class WorkItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WorkListRepositoryImpl(application) //temp

    private val addWorkItemUseCase = AddWorkItemUseCase(repository)
    private val editWorkItemUSeCase = EditWorkItemUSeCase(repository)
    private val getWorkItemUseCase = GetWorkItemUseCase(repository)

    private val _workItem = MutableLiveData<WorkItem>()
    val workItem: LiveData<WorkItem>
        get() = _workItem

    private val _errorInputDate = MutableLiveData<Boolean>()
    val errorInputDate: LiveData<Boolean>
        get() = _errorInputDate

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


    fun resetErrorInputDate() {
        _errorInputDate.value = false
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
        if (date <= 0) {
            _errorInputDate.value = true
            result = false
        }
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