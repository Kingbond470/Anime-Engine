package dev.kingbond.yourtodolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface YourTodoDao {

    @Insert()
     fun insertTask(todoModel: YourTodoModel): Long

    @Query("Select * from YourTodoModel where isFinished == 0")
    fun getTask(): LiveData<List<YourTodoModel>>

    @Query("Update YourTodoModel Set isFinished = 1 where id=:uid")
    fun finishTask(uid: Long)

    @Query("Delete from YourTodoModel where id=:uid")
    fun deleteTask(uid: Long)
}