package ru.nvgsoft.worklist.presentation

import android.content.Context
import android.os.Bundle
import android.service.chooser.ChooserAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.databinding.FragmentWorkListBinding
import javax.inject.Inject

class WorkListFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: WorkListViewModel
    private lateinit var workListAdapter: WorkListAdapter
    private lateinit var binding: FragmentWorkListBinding

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
        binding = FragmentWorkListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerList(view)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WorkListViewModel::class.java)
        viewModel.workList.observe(requireActivity()) {

            Log.d("MainActivity", it.toString())
            workListAdapter.submitList(it)
        }


        binding.buttonAddWorkItem.setOnClickListener {
//           requireActivity().supportFragmentManager.beginTransaction()
//               .replace(R.id.main_container, WorkItemFragment.newInstanceAddItem())
//               .addToBackStack(null)
//               .commit()

            findNavController().navigate(WorkListFragmentDirections.actionNavHomeToWorkItemFragment() )
        }
    }


    private fun setupRecyclerList(view: View) {

        workListAdapter = WorkListAdapter()
        binding.rvWorkList.adapter = workListAdapter
        setupOnClickListener()
        setupSwipeListener(binding.rvWorkList)
    }

    private fun setupOnClickListener() {
        workListAdapter.onWorkItemClickListener = {
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.main_container, WorkItemFragment.newInstanceEditItem(it.id))
//                .addToBackStack(null)
//                .commit()
            findNavController().navigate(WorkListFragmentDirections.actionNavHomeToWorkItemFragment("mode_edit", it.id) )
        }


    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = workListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteWorkItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    companion object{

        fun newInstance(): WorkListFragment{
          return WorkListFragment()
        }
    }
}