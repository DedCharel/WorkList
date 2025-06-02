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

    //ORGANIZATION

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrganization(organizationDbModel: OrganizationDbModel)

    @Query("SELECT * FROM organization")
    fun getOrganizationList(): LiveData<List<OrganizationDbModel>>

    @Query("DELETE FROM organization WHERE id =:organizationId ")
    suspend fun deleteOrganization(organizationId: Int)

    @Query("SELECT * FROM organization WHERE id=:organizationId LIMIT 1")
    suspend fun getOrganization(organizationId: Int): OrganizationDbModel



}