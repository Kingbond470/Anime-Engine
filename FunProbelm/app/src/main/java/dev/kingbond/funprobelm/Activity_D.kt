package dev.kingbond.funprobelm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_d.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnD

class Activity_D : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)

        btnD.setOnClickListener {
            val intent= Intent(this,Activity_E::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent= Intent(this,Activity_E::class.java)
//        startActivity(intent)
//    }
}