package ru.nvgsoft.worklist.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.databinding.FragmentWorkItemBinding
import ru.nvgsoft.worklist.domain.WorkItem
import ru.nvgsoft.worklist.utils.convertLongToDate

class WorkItemFragment: Fragment() {

    private lateinit var viewModel:WorkItemViewModel
    private lateinit var binding: FragmentWorkItemBinding


    private var screenMode: String = MODE_UNKNOWN
    private var workId: Int = WorkItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[WorkItemViewModel::class.java]
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
        observeInputError()
    }

    private fun parseParam(){
        val args =  requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw  RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(WORK_ITEM_ID)){
                throw RuntimeException("Params work item id is absent")
            }
            workId = args.getInt(WORK_ITEM_ID, WorkItem.UNDEFINED_ID)
        }
    }

    private fun addTextChangeListeners(){
        binding.etDate.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputDate()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etWorker.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputWorker()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etOrganisation.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputOrganisation()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etSpendTime.addTextChangedListener(object: TextWatcher {
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
            binding.tilDate.error = message
        }
        viewModel.errorInputWorker.observe(this){
            val message = if (it){
                getString(R.string.error_input_worker)
            } else {
                null
            }
            binding.tilWorker.error = message
        }
        viewModel.errorInputOrganisation.observe(this){
            val message = if (it){
                getString(R.string.error_input_organisation)
            } else {
                null
            }
            binding.tilOrganisation.error = message
        }
        viewModel.errorInputSpendTime.observe(this){
            val message = if (it){
                getString(R.string.error_input_spend_time)
            } else {
                null
            }
            binding.tilSpendTime.error = message
        }
    }
    private fun observeViewModel(){
        viewModel.shouldCloseScreen.observe(this){
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    private fun launchRightMode(){
        when (screenMode){
            MODE_EDIT -> launchEditScreenMode()
            MODE_ADD -> launchAddScreenMode()
        }
    }
    private fun launchEditScreenMode(){
        viewModel.getWorkItem(workId)

        viewModel.workItem.observe(this) {
            with(binding) {
            etDate.setText(convertLongToDate(it.date))
            etWorker.setText(it.worker)
            etOrganisation.setText(it.organisation)
            etDescription.setText(it.description)
            etSpendTime.setText(it.spendTime.toString())
            }
        }
        with(binding) {
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

    }
    private fun launchAddScreenMode(){
        with(binding) {
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
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val WORK_ITEM_ID = "extra_work_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""


        fun newInstanceAddItem(): WorkItemFragment{
            return WorkItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(itemId: Int): WorkItemFragment{
            return WorkItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(WORK_ITEM_ID, itemId)
                }
            }
        }

    }
}