package ru.nvgsoft.worklist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrganizationDao {

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