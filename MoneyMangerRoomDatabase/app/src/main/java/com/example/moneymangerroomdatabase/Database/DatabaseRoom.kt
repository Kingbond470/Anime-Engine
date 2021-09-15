package com.example.moneymangerroomdatabase.Database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [IncomeTable::class, ExpenseTable::class], version = 1)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun getMoneyDao(): DaoMoney

    companion object {
        private var INSTANCE: DatabaseRoom? = null

        fun getDatabaseObject(context: Context): DatabaseRoom {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext, DatabaseRoom::class.java, "money_database"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }

    }
}