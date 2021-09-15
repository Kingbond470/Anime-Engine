package com.example.moneymangerroomdatabase.Database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DaoMoney {

    @Insert
     fun insertToIncome(incomeTable: IncomeTable)

    @Update
     fun updateIncome(incomeTable: IncomeTable)

    @Delete()
     fun deleteIncome(incomeTable: IncomeTable)

    @Query("select * from INCOME_TABLE")
     fun getIncome(): LiveData<MutableList<IncomeTable>>

    @Query("select sum(amount)  as Total from INCOME_TABLE")
     fun getIncomeBalance(): LiveData<Double>


    @Insert
     fun insertToExpense(expenseTable: ExpenseTable)

    @Update
     fun updateExpense(expenseTable: ExpenseTable)

    @Delete
     fun deleteExpense(expenseTable: ExpenseTable)

    @Query("Select * from EXPENSE_TABLE")
     fun getExpense(): LiveData<MutableList<ExpenseTable>>

    @Query("select sum(amount) as Total from EXPENSE_TABLE")
     fun getExpenseBalance(): LiveData<Double>


}