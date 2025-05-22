package ru.nvgsoft.worklist.domain

data class WorkItem(
    val date: Long,
    val worker: String,
    val organisation: String,
    val description: String,
    val spendTime: Double,
    var id: Int = UNDEFINED_ID

) {
    companion object{
        const val UNDEFINED_ID = -1
    }
}