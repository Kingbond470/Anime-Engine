package com.example.moneymangerroomdatabase

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomDatabase
import com.example.moneymangerroomdatabase.Adapter.MoneyIncomeAdapter
import com.example.moneymangerroomdatabase.Adapter.OnItemClickListener
import com.example.moneymangerroomdatabase.Database.DaoMoney
import com.example.moneymangerroomdatabase.Database.DatabaseRoom
import com.example.moneymangerroomdatabase.Database.ExpenseTable
import com.example.moneymangerroomdatabase.Database.IncomeTable
import kotlinx.android.synthetic.main.fragment_income_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Income_Fragment : Fragment(),OnItemClickListener {

    private var list = mutableListOf<IncomeTable>()
    private lateinit var moneyAdapter: MoneyIncomeAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roomDatabase= context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        val moneyIncomeDao=roomDatabase.getMoneyDao()


        tvDatePickerUpdateIncome.text = SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis())

        var calendar = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                tvDatePickerUpdateIncome.text = sdf.format(calendar.time)

            }

        tvDatePickerUpdateIncome.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(
                    it1, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        moneyIncomeDao.getIncome().observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            moneyAdapter.notifyDataSetChanged()
        })


        moneyAdapter= MoneyIncomeAdapter(list, this)
        recyclerViewIncome.adapter = moneyAdapter
        recyclerViewIncome.layoutManager = LinearLayoutManager(context)


    }


    override fun onEditClicked(money: IncomeTable, position: Int) {

        updateRelativeIncome.visibility=View.VISIBLE

        val roomDatabase= context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        val  moneyIncomeDao=roomDatabase.getMoneyDao()

        btnCancelUpadteIncome.setOnClickListener {
            updateRelativeIncome.visibility=View.GONE
        }

        btnSubmitUpdateIncome.setOnClickListener {



            money.amount = etAmountUpdateIncome.text.toString().toDouble()
            money.desc = etDescriptionUpdateIncome.text.toString()

            money.date= tvDatePickerUpdateIncome.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                moneyIncomeDao.updateIncome(money)
            }

            updateRelativeIncome.visibility=View.GONE

        }


    }


    override fun onDeleteClicked(money: IncomeTable, position: Int) {
       // val dbHandler= context?.let { DatabaseHandler(it) }

        val roomDatabase= context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        val moneyIncomeDao=roomDatabase.getMoneyDao()

        CoroutineScope(Dispatchers.IO).launch {
            moneyIncomeDao.deleteIncome(money)
        }


    }



    override fun onEditClicked(money: ExpenseTable, position: Int) {
    }

    override fun onDeleteClicked(money: ExpenseTable, position: Int) {

    }

}