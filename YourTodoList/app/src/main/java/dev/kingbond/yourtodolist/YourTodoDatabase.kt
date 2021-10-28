package dev.kingbond.yourtodolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [YourTodoModel::class], version = 2)
abstract class YourTodoDatabase : RoomDatabase() {
    abstract fun todoDao(): YourTodoDao

    companion object {
        @Volatile
        private var INSTANCE: YourTodoDatabase? = null

        fun getDatabase(context: Context): YourTodoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YourTodoDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}