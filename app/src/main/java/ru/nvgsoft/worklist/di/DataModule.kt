package ru.nvgsoft.worklist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nvgsoft.worklist.data.AppDatabase
import ru.nvgsoft.worklist.data.OrganizationDao
import ru.nvgsoft.worklist.data.OrganizationRepositoryImpl
import ru.nvgsoft.worklist.data.WorkListDao
import ru.nvgsoft.worklist.data.WorkListRepositoryImpl
import ru.nvgsoft.worklist.domain.organization.OrganizationRepository
import ru.nvgsoft.worklist.domain.work_list.WorkListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindWorkListRepository(impl: WorkListRepositoryImpl): WorkListRepository

    @ApplicationScope
    @Binds
    fun bindOrganisationRepository(impl: OrganizationRepositoryImpl): OrganizationRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideWOrkListDao(
            application: Application
        ): WorkListDao{
            return AppDatabase.getInstance(application).workListDao()
        }

        @ApplicationScope
        @Provides
        fun provideOrganizationDao(
            application: Application
        ): OrganizationDao{
            return AppDatabase.getInstance(application).organizationDao()
        }
    }
}