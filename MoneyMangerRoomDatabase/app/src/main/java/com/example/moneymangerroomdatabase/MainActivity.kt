package com.example.moneymangerroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()

        ivAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setAdapter() {
        val pageAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = pageAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Income"
                1 -> tab.text = "Expense"
                2 -> tab.text = "Balance"
            }

        }.attach()
    }
}