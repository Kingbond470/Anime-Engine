package dev.kingbond.moneymanager.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.data.Money

class MoneyAdapter(private var list: List<Money>) :
    RecyclerView.Adapter<MoneyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_balance_layout, parent, false)
        return MoneyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        val data=list[position]
        val context=holder.amount.context

        if(data.amount>=0){
            holder.amount.text="+ $%.2f".format(data.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context,R.color.greeen))
        }else{
            holder.amount.text="- $%.2f".format(Math.abs(data.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context,R.color.red))

        }
        holder.des.text=data.description
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(money: List<Money>){
        this.list=money
        notifyDataSetChanged()
    }
}

class MoneyViewHolder(moneyView: View) : RecyclerView.ViewHolder(moneyView) {

    val des: TextView = moneyView.findViewById(R.id.tvDescription)
    val amount: TextView = moneyView.findViewById(R.id.tvAmount)

}