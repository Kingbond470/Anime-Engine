package dev.kingbond.moneymanager.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Money::class), version = 1)
 abstract class MoneyDatabase :RoomDatabase(){
     abstract fun moneyDao():MoneyDao
}