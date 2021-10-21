package dev.kingbond.moneymanager.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.room.Room
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.data.Money
import dev.kingbond.moneymanager.data.MoneyDao
import dev.kingbond.moneymanager.data.MoneyDatabase
import kotlinx.android.synthetic.main.activity_add_money.*
import kotlinx.android.synthetic.main.activity_add_money.labelLayout
import kotlinx.android.synthetic.main.activity_add_money.moneyLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.text.InputFilter
import android.text.InputFilter.LengthFilter


class AddMoneyActivity : AppCompatActivity() {

    private lateinit var db: MoneyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_money)

        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)


        etMoney.doAfterTextChanged {

            if (etMoney.text.toString().trim().length == 10) {
                etMoney.filters = arrayOf<InputFilter>(LengthFilter(10))
                etMoney.error = "Max limit reached"
            }
        }

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
            } else if (amount == null) {
                etMoney.error = "Please enter a valid amount"
            } else {
                val money = Money(0, label, amount, desc)
                insert(money)
            }
            Toast.makeText(this,"Added Successfully !!",Toast.LENGTH_SHORT).show()
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