package ru.nvgsoft.worklist.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.databinding.FragmentWorkListBinding

class WorkListFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var workListAdapter: WorkListAdapter
    private lateinit var binding: FragmentWorkListBinding

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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.workList.observe(this) {

            Log.d("MainActivity", it.toString())
            workListAdapter.submitList(it)
        }


        binding.buttonAddWorkItem.setOnClickListener {
           requireActivity().supportFragmentManager.beginTransaction()
               .replace(R.id.main_container, WorkItemFragment.newInstanceAddItem())
               .addToBackStack(null)
               .commit()
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
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, WorkItemFragment.newInstanceEditItem(it.id))
                .addToBackStack(null)
                .commit()
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