package ru.nvgsoft.worklist.presentation.organization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.nvgsoft.worklist.databinding.FargmentOrganizationItemBinding
import ru.nvgsoft.worklist.databinding.FragmentOrganizationListBinding
import ru.nvgsoft.worklist.databinding.FragmentWorkItemBinding
import ru.nvgsoft.worklist.presentation.ViewModelFactory
import ru.nvgsoft.worklist.presentation.WorkListApp
import javax.inject.Inject

class OrganizationItemFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as WorkListApp).component
    }

    private lateinit var viewModel: OrganizationItemViewModel
    private lateinit var binding: FargmentOrganizationItemBinding

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FargmentOrganizationItemBinding.inflate(inflater, container, false)
        return binding.root
    }
}