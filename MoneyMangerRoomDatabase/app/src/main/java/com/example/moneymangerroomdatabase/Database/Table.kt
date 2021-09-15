package com.example.moneymangerroomdatabase.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "INCOME_TABLE")
data class IncomeTable(
    @ColumnInfo(name="amount") var amount:Double,
    @ColumnInfo(name = "date") var date:String,
    @ColumnInfo(name = "description") var desc:String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int?=null
}


@Entity(tableName = "EXPENSE_TABLE")
data class ExpenseTable(
    @ColumnInfo(name="amount") var amount:Double,
    @ColumnInfo(name = "date") var date:String,
    @ColumnInfo(name = "description") var desc:String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int?=null
}