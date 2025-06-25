package ru.nvgsoft.worklist.presentation.organization

import androidx.lifecycle.ViewModel
import ru.nvgsoft.worklist.domain.organization.DeleteOrganizationUseCase
import ru.nvgsoft.worklist.domain.organization.GetOrganizationListUseCase
import javax.inject.Inject

class OrganizationListViewModel @Inject constructor(
    private val getOrganizationListUseCase: GetOrganizationListUseCase,
    private val deleteOrganizationUseCase: DeleteOrganizationUseCase
): ViewModel() {

    val organizationList = getOrganizationListUseCase.getOrganizationList()


}