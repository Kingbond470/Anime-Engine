package dev.kingbond.moneymanager.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import dev.kingbond.moneymanager.R
import kotlinx.android.synthetic.main.activity_add_money.*

class AddMoneyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_money)

        etLabel.addTextChangedListener {
            if(it?.count()!! >0){
                labelLayout.error=null
            }
        }

        etMoney.addTextChangedListener{
            if(it?.count()!!>0){
                moneyLayout.error=null
            }
        }

        btnAddMoney.setOnClickListener {
            val label=etLabel.text.toString()
            val amount=etMoney.text.toString().toDoubleOrNull()

            if(label.isEmpty()){
                etLabel.error="Please enter a valid label"
            }
            if(amount==null){
                etMoney.error="Please enter a valid amount"
            }
            val desc=etDescription.text.toString()
        }

        btnClose.setOnClickListener {
            finish()
        }
    }
}