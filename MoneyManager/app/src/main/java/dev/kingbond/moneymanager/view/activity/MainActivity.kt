package dev.kingbond.moneymanager.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.view.adapter.MoneyAdapter
import dev.kingbond.moneymanager.view.model.Money
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<Money>
    private lateinit var adapter: MoneyAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = arrayListOf(
            Money("Money is ", 40.0),
            Money("Food is ", -40.0),
            Money("Eating is ", 40.0),
            Money("Camera is ", -70.0),
            Money("travel is ", 100.0),
            Money("Hiking is ", -80.0),
            Money("Anime is ", 400.0),
            Money("Movie is ", -470.0),
            Money("Freeland is ", 8940.0)
        )

        setRecyclerView()
        updateDashboard()
    }

    private fun setRecyclerView() {
        adapter = MoneyAdapter(list)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }

    private fun updateDashboard() {
        val totalAmount = list.map { it.amount }.sum()
        val income = list.filter {
            it.amount > 0
        }.map { it.amount }.sum()

        val expense=totalAmount-income

        tvBalance.text="$%.2f".format(totalAmount)
        tvIncome.text="$%.2f".format(income)
        tvExpense.text="$%.2f".format(expense)

    }
}