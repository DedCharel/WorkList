package ru.nvgsoft.worklist.presentation.organization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nvgsoft.worklist.domain.organization.AddOrganizationUseCase
import ru.nvgsoft.worklist.domain.organization.EditOrganizationUseCae
import ru.nvgsoft.worklist.domain.organization.GetOrganizationUseCase
import ru.nvgsoft.worklist.domain.organization.Organization
import javax.inject.Inject

class OrganizationItemViewModel @Inject constructor(
    private val addOrganizationUseCase: AddOrganizationUseCase,
    private val editOrganizationUseCae: EditOrganizationUseCae,
    private val getOrganizationUseCase: GetOrganizationUseCase
): ViewModel() {

    private val _organization = MutableLiveData<Organization>()
    val organization: LiveData<Organization>
        get() =  _organization

    fun getOrganization(organizationId: Int){
        viewModelScope.launch {
            val item = getOrganizationUseCase.getOrganization(organizationId)
            _organization.value = item
        }
    }
}