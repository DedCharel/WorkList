package ru.nvgsoft.worklist.presentation.organization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.nvgsoft.worklist.databinding.FragmentOrganizationListBinding
import ru.nvgsoft.worklist.presentation.ViewModelFactory
import ru.nvgsoft.worklist.presentation.WorkListApp
import javax.inject.Inject

class OrganizationListFragment(): Fragment()
{

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: OrganizationListViewModel
    private lateinit var binding: FragmentOrganizationListBinding

    private val component by lazy {
        (requireActivity().application as WorkListApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrganizationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[OrganizationListViewModel::class.java]

    }
}