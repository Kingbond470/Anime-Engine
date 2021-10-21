package dev.kingbond.moneymanager.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.view.adapter.MoneyAdapter
import dev.kingbond.moneymanager.data.Money
import dev.kingbond.moneymanager.data.MoneyDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var deleteMoney: Money

    private lateinit var list: List<Money>
    private lateinit var oldList: List<Money>
    private lateinit var adapter: MoneyAdapter
    private lateinit var layoutManager: LinearLayoutManager

    // database
    private lateinit var db: MoneyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.textMain)

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
        //fetchAll()

        // swipe to remove
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteMoney(list[viewHolder.adapterPosition])
            }

        }


        // item touch helper
        val swiperHelper = ItemTouchHelper(itemTouchHelper)
        swiperHelper.attachToRecyclerView(recyclerView)

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

        var totAmt = totalAmount
        when {
            abs(totAmt) > 1000000 -> {
                totAmt = totalAmount / 1000000
                tvBalance.text = "₹ %.2f M".format(totAmt)
            }
            abs(totalAmount) > 1000 -> {
                totAmt = totalAmount / 1000
                tvBalance.text = "₹ %.2f K".format(totAmt)
            }
            else -> tvBalance.text = "₹ %.2f".format(totalAmount)
        }

        var totIncome = income
        when {
            totIncome > 1000000 -> {
                totIncome = income / 1000000
                tvIncome.text = "₹ %.2f M".format(totIncome)
            }
            totIncome > 1000 -> {
                totIncome = income / 1000
                tvIncome.text = "₹ %.2f K".format(totIncome)
            }
            else -> tvIncome.text = "₹ %.2f".format(totIncome)
        }

        var totExpense = expense
        when {
            abs(totExpense) > 1000000 -> {
                totExpense = expense / 1000000
                tvExpense.text = "₹ %.2f M".format(totExpense)
            }
            abs(totExpense) > 1000 -> {
                totExpense = expense / 1000
                tvExpense.text = "₹ %.2f K".format(totExpense)
            }
            else -> tvExpense.text = "₹ %.2f".format(totExpense)
        }

//        tvIncome.text = "₹ %.2f".format(income)
//        tvExpense.text = "₹ %.2f".format(expense)

    }

    private fun fetchAll() {
        GlobalScope.launch {
            //db.moneyDao().insertMoney(Money(0,"Ice Cream", -3.0,"Yumy"))
            list = db.moneyDao().getAll()
            runOnUiThread {
                updateDashboard()
                adapter.setData(list)
            }
        }
    }

    private fun deleteMoney(money: Money) {
        deleteMoney = money
        oldList = list

        GlobalScope.launch {
            db.moneyDao().delete(money)
            list = list.filter { it.id != money.id }
        }
        runOnUiThread {
//            updateDashboard()
//            adapter.setData(list)
            fetchAll()
            showSnackBar()
        }
    }

    private fun showSnackBar() {
        val view = coordinator
        val snackbar = Snackbar.make(view, "Money Deleted !", Snackbar.LENGTH_SHORT)
        snackbar.setAction("Undo") {
            undoDelete()
        }
            .setActionTextColor(ContextCompat.getColor(this, R.color.red))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }

    private fun undoDelete() {
        GlobalScope.launch {
            db.moneyDao().insertMoney(deleteMoney)
            list = oldList
            runOnUiThread {
                fetchAll()
//               updateDashboard()
//               adapter.setData(list)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchAll()
    }
}