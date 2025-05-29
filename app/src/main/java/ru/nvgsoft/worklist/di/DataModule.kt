package ru.nvgsoft.worklist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nvgsoft.worklist.data.AppDatabase
import ru.nvgsoft.worklist.data.WorkListDao
import ru.nvgsoft.worklist.data.WorkListRepositoryImpl
import ru.nvgsoft.worklist.domain.WorkListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindWorkListRepository(impl: WorkListRepositoryImpl): WorkListRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideWOrkListDao(
            application: Application
        ): WorkListDao{
            return AppDatabase.getInstance(application).workListDao()
        }
    }
}