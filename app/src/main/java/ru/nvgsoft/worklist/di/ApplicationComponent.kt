package ru.nvgsoft.worklist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.nvgsoft.worklist.presentation.WorkItemFragment
import ru.nvgsoft.worklist.presentation.WorkListFragment
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

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}