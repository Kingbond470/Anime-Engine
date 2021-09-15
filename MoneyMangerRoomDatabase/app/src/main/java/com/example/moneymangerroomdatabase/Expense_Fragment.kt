package com.example.moneymangerroomdatabase

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymangerroomdatabase.Adapter.MoneyExpenseAdapter
import com.example.moneymangerroomdatabase.Adapter.OnItemClickListener
import com.example.moneymangerroomdatabase.Database.DaoMoney
import com.example.moneymangerroomdatabase.Database.DatabaseRoom
import com.example.moneymangerroomdatabase.Database.ExpenseTable
import com.example.moneymangerroomdatabase.Database.IncomeTable
import kotlinx.android.synthetic.main.fragment_expense_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Expense_Fragment : Fragment(), OnItemClickListener {
    private var list = mutableListOf<ExpenseTable>()
    lateinit var moneyAdapter: MoneyExpenseAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val roomDatabase = context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        val moneyExpenseDao = roomDatabase.getMoneyDao()

        super.onViewCreated(view, savedInstanceState)


        tvDatePickerUpdate.text = SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis())

        var calendar = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                tvDatePickerUpdate.text = sdf.format(calendar.time)

            }

        tvDatePickerUpdate.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(
                    it1, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        //   dbHandler?.let { list.addAll(it.getExpense()) }

        moneyAdapter = MoneyExpenseAdapter(list, this)
        recyclerViewExpense.adapter = moneyAdapter
        recyclerViewExpense.layoutManager = LinearLayoutManager(context)

        moneyExpenseDao.getExpense().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            list.clear()
            list.addAll(it)
            moneyAdapter.notifyDataSetChanged()
        })

    }


    override fun onEditClicked(money: ExpenseTable, position: Int) {
        updateRelativeExpense.visibility = View.VISIBLE

        val roomDatabase = context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        val moneyExpenseDao = roomDatabase.getMoneyDao()

        btnCancelUpadte.setOnClickListener {
            updateRelativeExpense.visibility = View.GONE
        }

        btnSubmitUpdate.setOnClickListener {
            money.amount = etAmountUpdate.text.toString().toDouble()
            money.desc = etDescription.text.toString()
            money.date = tvDatePickerUpdate.text.toString()


            CoroutineScope(Dispatchers.IO).launch {
                moneyExpenseDao.updateExpense(money)
            }

            updateRelativeExpense.visibility = View.GONE

        }
    }


    override fun onDeleteClicked(money: ExpenseTable, position: Int) {
        val roomDatabase = context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        val moneyExpenseDao = roomDatabase.getMoneyDao()

        CoroutineScope(Dispatchers.IO).launch {
            moneyExpenseDao.deleteExpense(money)
        }

    }


    override fun onEditClicked(money: IncomeTable, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClicked(money: IncomeTable, position: Int) {
        TODO("Not yet implemented")
    }


}