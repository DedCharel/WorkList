package ru.nvgsoft.worklist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.domain.WorkItem

class WorkItemActivity : AppCompatActivity(), WorkItemFragment.OnFragmentWorkItemFinishedListener {

    private var screenMode: String = MODE_UNKNOWN
    private var workItemId: Int = WorkItem.UNDEFINED_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }

    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_WORK_ITEM_ID)) {
                throw RuntimeException("Param work item id is absent")
            }
            workItemId = intent.getIntExtra(EXTRA_WORK_ITEM_ID, WorkItem.UNDEFINED_ID)
        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> WorkItemFragment.newInstanceAddItem()
            MODE_EDIT -> WorkItemFragment.newInstanceEditItem(workItemId)
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.work_item_container, fragment)
            .commit()
    }

    override fun onFragmentWorkItemFinished() {
        finish()
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_WORK_ITEM_ID = "extra_work_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, WorkItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, workItemId: Int): Intent {
            val intent = Intent(context, WorkItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_WORK_ITEM_ID, workItemId)
            return intent
        }
    }


}