package dev.kingbond.funprobelm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_b.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnB

class Activity_B : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        btnB.setOnClickListener {
            val intent= Intent(this,Activity_C::class.java)
            startActivity(intent)
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent= Intent(this,Activity_A::class.java)
//        startActivity(intent)
//    }
}