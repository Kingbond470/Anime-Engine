package dev.kingbond.moneymanager.data

import androidx.room.*

@Dao
interface MoneyDao {
    @Query("select * from money")
    fun getAll():List<Money>

    @Insert
    fun insertMoney(vararg money: Money)

    @Delete
    fun delete(money: Money)

    @Update
    fun update(vararg money: Money)
}