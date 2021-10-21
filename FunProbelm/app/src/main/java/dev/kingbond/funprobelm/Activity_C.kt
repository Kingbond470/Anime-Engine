package dev.kingbond.funprobelm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_c.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnC

class Activity_C : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        btnC.setOnClickListener {
            val intent= Intent(this,Activity_D::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent= Intent(this,Activity_B::class.java)
//        startActivity(intent)
//    }
}