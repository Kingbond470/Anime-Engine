package dev.kingbond.yourtodolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_todo_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

const val DB_NAME = "yourtodo.db"

class TodoTaskActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var myCalendar: Calendar

    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    private var finalDate = 0L
    private var finalTime = 0L

    private val labels = arrayListOf("Personal", "Work", "Health", "Banking", "Others")

    private val db by lazy {
        YourTodoDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_task)
        setSupportActionBar(newTaskToolbar)
        title = "New Task"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dateEdt.setOnClickListener(this)
        timeEdt.setOnClickListener(this)
        saveBtn.setOnClickListener(this)

        setUpSpinner()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpSpinner() {
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, labels)
        spinnerCategory.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dateEdt -> {
                setDateListener()
            }
            R.id.timeEdt -> {
                setTimeListener()
            }
            R.id.saveBtn -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo() {
        val category = spinnerCategory.selectedItem.toString()
        val title = titleInpLay.editText?.text.toString()
        val description = taskInpLay.editText?.text.toString()
        val radioButtonID = priorityInput.checkedRadioButtonId
        val checkRadioButton = findViewById<RadioButton>(radioButtonID)
        val rbText = checkRadioButton.text.toString()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                return@withContext db.todoDao().insertTask(
                    YourTodoModel(
                        title = title,
                        description = description,
                        category = category,
                        priority = rbText,
                        date = finalDate,
                        time = finalTime
                    )
                )
            }
            finish()
        }
    }

    private fun setDateListener() {
        myCalendar = Calendar.getInstance()

        dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalendar.set(year, month, dayOfMonth)
                updateDate()
            }

        val datePickerDialog = DatePickerDialog(
            this,
            dateSetListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()

        timeSetListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, minute)
                updateTime()
            }

        val timePickerDialog = TimePickerDialog(
            this,
            timeSetListener,
            myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),
            false
        )

        timePickerDialog.show()
    }

    private fun updateDate() {
        val myFormat = "EEE, dd/MM/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        finalDate = myCalendar.time.time
        dateEdt.setText(sdf.format(myCalendar.time))
    }

    private fun updateTime() {
        val myFormat = "h:mm a"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        finalTime = myCalendar.time.time
        timeEdt.setText(sdf.format(myCalendar.time))
    }

}