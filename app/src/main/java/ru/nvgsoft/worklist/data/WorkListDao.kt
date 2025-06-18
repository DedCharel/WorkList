package ru.nvgsoft.worklist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkListDao {

    //WORK_ITEM

    @Query("SELECT * FROM work_item")
    fun getWorkList(): LiveData<List<WorkItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkItem(workItemDbModel: WorkItemDbModel)

    @Query("DELETE FROM work_item WHERE id =:worItemId ")
    suspend fun deleteWorkItem(worItemId: Int)

    @Query("SELECT * FROM work_item WHERE id=:workItemId LIMIT 1")
    suspend fun getWorkItem(workItemId: Int): WorkItemDbModel




}