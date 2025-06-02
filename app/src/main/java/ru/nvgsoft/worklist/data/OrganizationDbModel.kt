package ru.nvgsoft.worklist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organization")
data class OrganizationDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val email: String,
    val phone:String
)
