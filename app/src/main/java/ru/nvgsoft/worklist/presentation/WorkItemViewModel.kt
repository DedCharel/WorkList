package ru.nvgsoft.worklist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nvgsoft.worklist.data.WorkListRepositoryImpl
import ru.nvgsoft.worklist.domain.AddWorkItemUseCase
import ru.nvgsoft.worklist.domain.EditWorkItemUSeCase
import ru.nvgsoft.worklist.domain.GetWorkItemUseCase
import ru.nvgsoft.worklist.domain.WorkItem

class WorkItemViewModel : ViewModel() {

    private val repository = WorkListRepositoryImpl() //temp

    private val addWorkItemUseCase = AddWorkItemUseCase(repository)
    private val editWorkItemUSeCase = EditWorkItemUSeCase(repository)
    private val getWorkItemUseCase = GetWorkItemUseCase(repository)

    private val workItem = MutableLiveData<WorkItem>()


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
            addWorkItemUseCase.addWorkItem(
                WorkItem(
                    date = newDate,
                    worker = newWorker,
                    organisation = newOrganisation,
                    description = newDescription,
                    spendTime = newSpendTime
                )
            )
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
            workItem.value?.let {
                val item = it.copy(
                            date = newDate,
                            worker = newWorker,
                            organisation = newOrganisation,
                            description = newDescription,
                            spendTime = newSpendTime
                        )
                editWorkItemUSeCase.editWorkItem(item)
            }
        }
    }

    private fun getWorkItem(itemId: Int) {
        val item = getWorkItemUseCase.getWorkItem(itemId)
        workItem.value = item
    }


    private fun parseDate(date: String?): Long {
        return try {
            date?.toLong() ?: 0
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
            //TODO input date error
            result = false
        }
        if (worker.isBlank()) {
            //TODO input worker error
            result = false
        }
        if (organisation.isBlank()) {
            //TODO input organisation error
            result = false
        }
        if (spendTime <= 0) {
            ////TODO input spend time error
            result = false
        }
        return result
    }


}