package dev.kingbond.funprobelm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_e.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnE

class Activity_E : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e)

        btnE.setOnClickListener {
            val intent= Intent(this,Activity_F::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }


//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent= Intent(this,Activity_C::class.java)
//        startActivity(intent)
//    }
}