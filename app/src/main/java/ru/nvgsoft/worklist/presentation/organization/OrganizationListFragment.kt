package ru.nvgsoft.worklist.presentation.organization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.nvgsoft.worklist.databinding.FragmentOrganizationListBinding
import ru.nvgsoft.worklist.presentation.ViewModelFactory
import ru.nvgsoft.worklist.presentation.WorkListApp
import ru.nvgsoft.worklist.presentation.work.WorkListAdapter
import ru.nvgsoft.worklist.presentation.work.WorkListFragmentDirections
import javax.inject.Inject

class OrganizationListFragment(): Fragment()
{

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: OrganizationListViewModel
    private lateinit var binding: FragmentOrganizationListBinding
    private lateinit var organizationListAdapter: OrganizationListAdapter

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
        setupRecyclerList(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[OrganizationListViewModel::class.java]
        viewModel.organizationList.observe(requireActivity()){
            organizationListAdapter.submitList(it)
        }

        binding.buttonAddOrganizationItem.setOnClickListener {
            findNavController().navigate(OrganizationListFragmentDirections.actionNavOrganizationToOrganizationItemFragment())
        }
    }

    private fun setupRecyclerList(view: View) {

        organizationListAdapter = OrganizationListAdapter()
        binding.rvOrganizationList.adapter = organizationListAdapter
        organizationListAdapter.onOrganizationItemClick = {
            findNavController().navigate(WorkListFragmentDirections.actionNavHomeToWorkItemFragment("mode_edit", it.id))
        }
    }
}