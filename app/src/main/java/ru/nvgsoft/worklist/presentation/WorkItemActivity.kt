package ru.nvgsoft.worklist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ru.nvgsoft.worklist.R

class WorkItemActivity : AppCompatActivity() {

    private lateinit var tilDate: TextInputLayout
    private lateinit var etDate: EditText
    private lateinit var tilWorker: TextInputLayout
    private lateinit var etWorker: EditText
    private lateinit var tilOrganisation: TextInputLayout
    private lateinit var etOrganisation: EditText
    private lateinit var tilDescription: TextInputLayout
    private lateinit var etDescription: EditText
    private lateinit var tilSpendTime: TextInputLayout
    private lateinit var etSpendTime: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_item)
        initViews()
    }

    private fun initViews() {
        tilDate = findViewById(R.id.til_date)
        etDate = findViewById(R.id.et_date)
        tilWorker = findViewById(R.id.til_worker)
        etWorker = findViewById(R.id.et_worker)
        tilOrganisation = findViewById(R.id.til_organisation)
        etOrganisation = findViewById(R.id.et_organisation)
        tilDescription = findViewById(R.id.til_description)
        etDescription = findViewById(R.id.et_description)
        tilSpendTime = findViewById(R.id.til_spend_time)
        etSpendTime = findViewById(R.id.et_spend_time)
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_WORK_ITEM_ID = "extra_work_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"

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