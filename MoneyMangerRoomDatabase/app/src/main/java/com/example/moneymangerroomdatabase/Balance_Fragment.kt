package com.example.moneymangerroomdatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.moneymangerroomdatabase.Database.DaoMoney
import com.example.moneymangerroomdatabase.Database.DatabaseRoom
import kotlinx.android.synthetic.main.fragment_balance_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.exp


class Balance_Fragment : Fragment(R.layout.fragment_balance_) {

    private lateinit var roomDatabase: DatabaseRoom
    lateinit var moneyExpenseDao: DaoMoney
    lateinit var moneyIncomeDao: DaoMoney


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        roomDatabase = context?.let { DatabaseRoom.getDatabaseObject(it) }!!
        moneyExpenseDao = roomDatabase.getMoneyDao()
        moneyIncomeDao = roomDatabase.getMoneyDao()


        var income=0.0
        var expense=0.0


        moneyIncomeDao.getIncomeBalance().observe(viewLifecycleOwner, Observer {
            if(it==null){
                tvIncome.text="0.0"
            }else{
                tvIncome.text=it.toString()
                income=it
                tvTotal.text=(income-expense).toString()
            }
        })


        moneyExpenseDao.getExpenseBalance().observe(viewLifecycleOwner, Observer {
            if(it==null){
                tvExpense.text="0.0"
            }else{
                tvExpense.text=it.toString()
                expense=it
                tvTotal.text=(income-expense).toString()
            }

        })

//        CoroutineScope(Dispatchers.IO).launch {
//            tvTotal.text=(res1.toString().toDouble()-res2.toString().toDouble()).toString()
//        }

        //tvTotal.text=(tvIncome.text.toString().toDouble()-tvExpense.text.toString().toDouble()).toString()

    }
}