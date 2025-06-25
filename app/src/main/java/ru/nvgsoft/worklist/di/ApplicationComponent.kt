package ru.nvgsoft.worklist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.nvgsoft.worklist.presentation.organization.OrganizationItemFragment
import ru.nvgsoft.worklist.presentation.work.WorkItemFragment
import ru.nvgsoft.worklist.presentation.work.WorkListFragment
import ru.nvgsoft.worklist.presentation.organization.OrganizationListFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: WorkListFragment)

    fun inject(fragment: WorkItemFragment)

    fun inject(fragment: OrganizationListFragment)

    fun inject(fragment: OrganizationItemFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}