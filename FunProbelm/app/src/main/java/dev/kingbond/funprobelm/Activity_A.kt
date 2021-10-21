package dev.kingbond.funprobelm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class Activity_A : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnA.setOnClickListener {
            val intent=Intent(this,Activity_B::class.java)
            startActivity(intent)
        }
    }
}