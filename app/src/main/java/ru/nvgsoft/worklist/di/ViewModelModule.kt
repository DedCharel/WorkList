package ru.nvgsoft.worklist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nvgsoft.worklist.presentation.organization.OrganizationItemViewModel
import ru.nvgsoft.worklist.presentation.work.WorkListViewModel
import ru.nvgsoft.worklist.presentation.work.WorkItemViewModel
import ru.nvgsoft.worklist.presentation.organization.OrganizationListViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(OrganizationListViewModel::class)
    fun bindOrganizationListViewModel(viewmodel: OrganizationListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrganizationItemViewModel::class)
    fun bindOrganizationItemViewModel(viewmodel: OrganizationItemViewModel): ViewModel

}