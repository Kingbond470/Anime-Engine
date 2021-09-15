package com.example.moneymangerroomdatabase

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moneymangerroomdatabase.Database.DaoMoney
import com.example.moneymangerroomdatabase.Database.DatabaseRoom
import com.example.moneymangerroomdatabase.Database.ExpenseTable
import com.example.moneymangerroomdatabase.Database.IncomeTable
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var roomDatabase: DatabaseRoom
    lateinit var moneyDao: DaoMoney

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        roomDatabase= DatabaseRoom.getDatabaseObject(this)
        moneyDao=roomDatabase.getMoneyDao()


        tvDatePicker.text = SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                tvDatePicker.text = sdf.format(cal.time)

            }

        tvDatePicker.setOnClickListener {
            DatePickerDialog(
                this@AddActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnSubmit.setOnClickListener {

            val tabName = spChoice.selectedItem.toString()
            val date = tvDatePicker.text.toString()
            val amount = etAmount.text.toString().toDouble()
            val desc = etDescription.text.toString()


            CoroutineScope(Dispatchers.IO).launch {
                if (tabName == "Income") {
                    val income=IncomeTable(amount,date, desc)
                    moneyDao.insertToIncome(income)
                    //  AddActivity().finish()
                } else {
                    val expense=ExpenseTable(amount,date,desc)
                    moneyDao.insertToExpense(expense)

                }
            }

            finish()

        }

    }

}