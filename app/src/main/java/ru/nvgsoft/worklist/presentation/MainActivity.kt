package ru.nvgsoft.worklist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.nvgsoft.worklist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var workListAdapter: WorkListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerList()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.workList.observe(this) {

            Log.d("MainActivity", it.toString())
            workListAdapter.submitList(it)
        }

        val buttonAdd = findViewById<FloatingActionButton>(R.id.button_add_work_item)
        buttonAdd.setOnClickListener {
            val intent = WorkItemActivity.newIntentAdd(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerList() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_work_list)
        workListAdapter = WorkListAdapter()
        recyclerView.adapter = workListAdapter
        setupOnClickListener()
        setupSwipeListener(recyclerView)
    }

    private fun setupOnClickListener() {
        workListAdapter.onWorkItemClickListener = {
            Log.d("MainActivity", it.toString())
            val intent = WorkItemActivity.newIntentEdit(this, it.id)
            startActivity(intent)
            Log.d("MainActivity", it.toString())
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

}