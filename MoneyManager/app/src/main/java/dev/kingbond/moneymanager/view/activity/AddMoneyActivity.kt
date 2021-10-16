package dev.kingbond.moneymanager.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.data.Money
import dev.kingbond.moneymanager.data.MoneyDao
import dev.kingbond.moneymanager.data.MoneyDatabase
import kotlinx.android.synthetic.main.activity_add_money.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddMoneyActivity : AppCompatActivity() {

    private lateinit var db: MoneyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_money)



        etLabel.addTextChangedListener {
            if (it?.count()!! > 0) {
                labelLayout.error = null
            }
        }

        etMoney.addTextChangedListener {
            if (it?.count()!! > 0) {
                moneyLayout.error = null
            }
        }

        btnAddMoney.setOnClickListener {
            val label = etLabel.text.toString()
            val amount = etMoney.text.toString().toDoubleOrNull()
            val desc = etDescription.text.toString()

            if (label.isEmpty()) {
                etLabel.error = "Please enter a valid label"
            }
            else if (amount == null) {
                etMoney.error = "Please enter a valid amount"
            }
            else{
                val money=Money(0,label,amount,desc)
                insert(money)
            }
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun insert(money: Money) {

        db = Room.databaseBuilder(this, MoneyDatabase::class.java, "money")
            .build()

        GlobalScope.launch {
            db.moneyDao().insertMoney(money)
            finish()
        }
    }
}