package dev.kingbond.yourtodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class TodoAdapter(private val list: List<YourTodoModel>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_layout, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todoModel: YourTodoModel) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                viewColorTag.setBackgroundColor(randomColor)
                txtShowTitle.text = todoModel.title
                txtShowTask.text = todoModel.description
                txtShowCategory.text = todoModel.category
                txtShowPriority.text = todoModel.priority
                updateTime(todoModel.time)
                updateDate(todoModel.date)

            }
        }

        private fun updateDate(date: Long) {
            val myFormat = "EEE, dd/MM/yy"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
            itemView.txtShowDate.text = sdf.format(Date(date))
        }

        private fun updateTime(time: Long) {
            val myFormat = "h:mm a"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
            itemView.txtShowTime.text = sdf.format(Date(time))
        }
    }

}