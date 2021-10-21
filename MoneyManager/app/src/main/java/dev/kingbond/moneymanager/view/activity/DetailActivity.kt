package dev.kingbond.moneymanager.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.room.Room
import dev.kingbond.moneymanager.R
import dev.kingbond.moneymanager.data.Money
import dev.kingbond.moneymanager.data.MoneyDatabase
import kotlinx.android.synthetic.main.activity_add_money.*
import kotlinx.android.synthetic.main.activity_add_money.btnClose
import kotlinx.android.synthetic.main.activity_add_money.etDescription
import kotlinx.android.synthetic.main.activity_add_money.etLabel
import kotlinx.android.synthetic.main.activity_add_money.etMoney
import kotlinx.android.synthetic.main.activity_add_money.labelLayout
import kotlinx.android.synthetic.main.activity_add_money.moneyLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var money:Money
    private lateinit var db: MoneyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)

         money = intent.getSerializableExtra("money") as Money
        etLabelUpdate.setText(money.label)
        etMoneyUpdate.setText(money.amount.toString())
        etDescriptionUpdate.setText(money.description)

        rootView.setOnClickListener {
            this.window.decorView.clearFocus()
            // to hide the keyboard
            val imp=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // deal with keyboard
            imp.hideSoftInputFromWindow(it.windowToken,0)
        }

        etMoneyUpdate.doAfterTextChanged {

            if(etMoneyUpdate.text.toString().trim().length==10){
                etMoneyUpdate.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))
                etMoneyUpdate.error="Max limit reached"
            }
        }

        etLabelUpdate.addTextChangedListener {
            btnUpdateMoney.visibility = View.VISIBLE
            if (it?.count()!! > 0) {
                labelLayout.error = null
            }
        }

        etMoneyUpdate.addTextChangedListener {
            btnUpdateMoney.visibility = View.VISIBLE
            if (it?.count()!! > 0) {
                moneyLayout.error = null
            }
        }

        etDescriptionUpdate.addTextChangedListener {
            btnUpdateMoney.visibility = View.VISIBLE
        }
        btnUpdateMoney.setOnClickListener {
            val label = etLabelUpdate.text.toString()
            val amount = etMoneyUpdate.text.toString().toDoubleOrNull()
            val desc = etDescriptionUpdate.text.toString()

            if (label.isEmpty()) {
                etLabelUpdate.error = "Please enter a valid Label"
            } else if (amount == null) {
                etMoneyUpdate.error = "Please enter a valid amount"
            } else {
                val money = Money(money.id, label, amount, desc)
                update(money)
            }
            Toast.makeText(this,"Updated Successfully", Toast.LENGTH_SHORT).show()
        }

        btnCloseUpdate.setOnClickListener {
            finish()
        }
    }

    private fun update(money: Money) {

        db = Room.databaseBuilder(this, MoneyDatabase::class.java, "money")
            .build()

        GlobalScope.launch {
            db.moneyDao().update(money)
            finish()
        }
    }

}