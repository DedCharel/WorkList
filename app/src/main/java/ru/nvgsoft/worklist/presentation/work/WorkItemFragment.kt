package ru.nvgsoft.worklist.presentation.work

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.nvgsoft.worklist.R
import ru.nvgsoft.worklist.databinding.FragmentWorkItemBinding
import ru.nvgsoft.worklist.domain.work_list.WorkItem
import ru.nvgsoft.worklist.presentation.ViewModelFactory
import ru.nvgsoft.worklist.presentation.WorkListApp
import ru.nvgsoft.worklist.utils.convertLongToDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class WorkItemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as WorkListApp).component
    }

    private lateinit var viewModel: WorkItemViewModel
    private lateinit var binding: FragmentWorkItemBinding
    private lateinit var calendar: Calendar
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener


    private var screenMode: String = MODE_UNKNOWN
    private var workId: Int = WorkItem.UNDEFINED_ID

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        viewModel = ViewModelProvider(this, viewModelFactory)[WorkItemViewModel::class.java]
        addTextChangeListeners()
        initDatePicker()
        launchRightMode()
        observeViewModel()
        observeInputError()
    }

    private fun initDatePicker() {

        calendar = Calendar.getInstance()

        dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                binding.tvDate.text = sdf.format(calendar.time)
            }

        binding.tvDate.setOnClickListener {
            DatePickerDialog(
                requireActivity(), dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun parseParam() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(WORK_ITEM_ID)) {
                throw RuntimeException("Params work item id is absent")
            }
            workId = args.getInt(WORK_ITEM_ID, WorkItem.UNDEFINED_ID)
        }
    }

    private fun addTextChangeListeners() {

        binding.etWorker.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputWorker()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etOrganisation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputOrganisation()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etSpendTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputSpendTime()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })


    }

    private fun observeInputError() {

        viewModel.errorInputWorker.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_worker)
            } else {
                null
            }
            binding.tilWorker.error = message
        }
        viewModel.errorInputOrganisation.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_organisation)
            } else {
                null
            }
            binding.tilOrganisation.error = message
        }
        viewModel.errorInputSpendTime.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_spend_time)
            } else {
                null
            }
            binding.tilSpendTime.error = message
        }
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditScreenMode()
            MODE_ADD -> launchAddScreenMode()
        }
    }

    private fun launchEditScreenMode() {
        viewModel.getWorkItem(workId)


        viewModel.workItem.observe(viewLifecycleOwner) {
            calendar.timeInMillis = it.date
            with(binding) {
                tvDate.text = convertLongToDate(it.date)
                etWorker.setText(it.worker)
                etOrganisation.setText(it.organisation)
                etDescription.setText(it.description)
                etSpendTime.setText(it.spendTime.toString())
            }
        }
        with(binding) {

            btnSave.setOnClickListener {
                viewModel.editWorkItem(
                    date = tvDate.text?.toString(),
                    worker = etWorker.text?.toString(),
                    organisation = etOrganisation.text?.toString(),
                    description = etDescription.text?.toString(),
                    spendTime = etSpendTime.text?.toString()
                )
            }
        }

    }

    private fun launchAddScreenMode() {
        with(binding) {
            tvDate.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
            btnSave.setOnClickListener {
                viewModel.addWorkItem(
                    date = tvDate.text?.toString(),
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


    }
}