package ru.nvgsoft.worklist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.utils.convertLongToDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WorkItemActivity : AppCompatActivity() {

    private lateinit var viewModel:WorkItemViewModel
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
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_item)
        initViews()
        viewModel = ViewModelProvider(this)[WorkItemViewModel::class.java]
        val screenMode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        when (screenMode){
            MODE_EDIT -> launchEditScreenMode()
            MODE_ADD -> launchAddScreenMode()
        }
        observeInputError()
        addTextChangeListeners()
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }



    }

    private fun addTextChangeListeners(){
        etDate.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputDate()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        etWorker.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputWorker()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        etOrganisation.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputOrganisation()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        etSpendTime.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputSpendTime()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })


    }

    private fun observeInputError(){
        viewModel.errorInputDate.observe(this){
            val message = if (it){
                getString(R.string.error_input_date)
            } else {
                null
            }
            tilDate.error = message
        }
        viewModel.errorInputWorker.observe(this){
            val message = if (it){
                getString(R.string.error_input_worker)
            } else {
                null
            }
            tilWorker.error = message
        }
        viewModel.errorInputOrganisation.observe(this){
            val message = if (it){
                getString(R.string.error_input_organisation)
            } else {
                null
            }
            tilOrganisation.error = message
        }
        viewModel.errorInputSpendTime.observe(this){
            val message = if (it){
                getString(R.string.error_input_spend_time)
            } else {
                null
            }
            tilSpendTime.error = message
        }
    }



    private fun launchEditScreenMode(){
        val workId = intent.getIntExtra(EXTRA_WORK_ITEM_ID, -1)
        viewModel.getWorkItem(workId)

        viewModel.workItem.observe(this){
            etDate.setText(convertLongToDate(it.date))
            etWorker.setText(it.worker)
            etOrganisation.setText(it.organisation)
            etDescription.setText(it.description)
            etSpendTime.setText(it.spendTime.toString())

        }

        btnSave.setOnClickListener {
            viewModel.editWorkItem(
                date = etDate.text?.toString(),
                worker = etWorker.text?.toString(),
                organisation = etOrganisation.text?.toString(),
                description = etDescription.text?.toString(),
                spendTime = etSpendTime.text?.toString()
            )
        }

    }
    private fun launchAddScreenMode(){
        btnSave.setOnClickListener {
            viewModel.addWorkItem(
                date = etDate.text?.toString(),
                worker = etWorker.text?.toString(),
                organisation = etOrganisation.text?.toString(),
                description = etDescription.text?.toString(),
                spendTime = etSpendTime.text?.toString()
            )
        }
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
        btnSave = findViewById(R.id.btn_save)
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