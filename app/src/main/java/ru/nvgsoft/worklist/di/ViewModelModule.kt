package ru.nvgsoft.worklist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nvgsoft.worklist.presentation.WorkListViewModel
import ru.nvgsoft.worklist.presentation.WorkItemViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WorkItemViewModel::class)
    fun bindWorkItemViewModel(viewmodel: WorkItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorkListViewModel::class)
    fun bindWorkListViewModel(viewmodel: WorkListViewModel): ViewModel

}