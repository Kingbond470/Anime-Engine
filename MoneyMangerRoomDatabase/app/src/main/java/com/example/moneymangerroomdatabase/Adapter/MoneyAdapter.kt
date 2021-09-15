package com.example.moneymangerroomdatabase.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymangerroomdatabase.Database.ExpenseTable
import com.example.moneymangerroomdatabase.Database.IncomeTable
import com.example.moneymangerroomdatabase.R
import kotlinx.android.synthetic.main.item_layout.view.*

class MoneyIncomeAdapter(
    private var list: MutableList<IncomeTable>,
    private var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MoneyIncomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyIncomeViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MoneyIncomeViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MoneyIncomeViewHolder, position: Int) {
        val money = list[position]
        holder.setData(money)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}

// view Holder
class MoneyIncomeViewHolder(itemView: View, var onItemClickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    fun setData(money: IncomeTable) {
        itemView.apply {
            tvAmount.text = money.amount.toString()
            tvDescrition.text = money.desc
            tvDate.text = money.date


            ivEdit.setOnClickListener {
                onItemClickListener.onEditClicked(money, adapterPosition)
            }

            ivDelete.setOnClickListener {
                onItemClickListener.onDeleteClicked(money, adapterPosition)
            }
        }
    }
}

class MoneyExpenseAdapter(
    private var list: MutableList<ExpenseTable>,
    private var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MoneyExpenseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyExpenseViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MoneyExpenseViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MoneyExpenseViewHolder, position: Int) {
        val money = list[position]
        holder.setData(money)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}

// view Holder
class MoneyExpenseViewHolder(itemView: View, var onItemClickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    fun setData(money: ExpenseTable) {
        itemView.apply {
            tvAmount.text = money.amount.toString()
            tvDescrition.text = money.desc
            tvDate.text = money.date


            ivEdit.setOnClickListener {
                onItemClickListener.onEditClicked(money, adapterPosition)
            }

            ivDelete.setOnClickListener {
                onItemClickListener.onDeleteClicked(money, adapterPosition)
            }
        }
    }


}


