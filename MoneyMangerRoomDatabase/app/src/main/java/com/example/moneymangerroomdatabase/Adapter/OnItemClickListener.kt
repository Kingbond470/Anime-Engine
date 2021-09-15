package com.example.moneymangerroomdatabase.Adapter

import com.example.moneymangerroomdatabase.Database.ExpenseTable
import com.example.moneymangerroomdatabase.Database.IncomeTable

interface OnItemClickListener {

    fun onEditClicked(money: IncomeTable,position: Int)
    fun onDeleteClicked(money: IncomeTable,position: Int)


    fun onEditClicked(money: ExpenseTable,position: Int)
    fun onDeleteClicked(money: ExpenseTable,position: Int)
}