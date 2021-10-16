package dev.kingbond.moneymanager.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.view.adapter.MoneyAdapter
import dev.kingbond.moneymanager.data.Money
import dev.kingbond.moneymanager.data.MoneyDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var list: List<Money>
    private lateinit var adapter: MoneyAdapter
    private lateinit var layoutManager: LinearLayoutManager

    // database
    private lateinit var db: MoneyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(this, MoneyDatabase::class.java, "money")
            .build()


//        list = arrayListOf(
//            Money("Money is ", 40.0),
//            Money("Food is ", -40.0),
//            Money("Eating is ", 40.0),
//            Money("Camera is ", -70.0),
//            Money("travel is ", 100.0),
//            Money("Hiking is ", -80.0),
//            Money("Anime is ", 400.0),
//            Money("Movie is ", -470.0),
//            Money("Freeland is ", 8940.0)
//        )

        list = arrayListOf()

        setRecyclerView()
        //updateDashboard()
        fetchAll()

        flAddButton.setOnClickListener {
            val intent = Intent(this, AddMoneyActivity::class.java)
            startActivity(intent)
        }
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

        val expense = totalAmount - income

        tvBalance.text = "$%.2f".format(totalAmount)
        tvIncome.text = "$%.2f".format(income)
        tvExpense.text = "$%.2f".format(expense)

    }

    private fun fetchAll(){
        GlobalScope.launch {
            db.moneyDao().insertMoney(Money(0,"Ice Cream", -3.0,"Yumy"))
            list=db.moneyDao().getAll()
            runOnUiThread {
                updateDashboard()
                adapter.setData(list)
            }
        }
    }
}