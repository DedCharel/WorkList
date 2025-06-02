package ru.nvgsoft.worklist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_item")
data class WorkItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val date: Long,
    val worker: String,
    val organisation: String,
    val description: String,
    val spendTime: Double
) {
}