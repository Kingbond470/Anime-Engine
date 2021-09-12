package com.example.kidsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // removing the status bar from top of application
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnStart.setOnClickListener {
            if (etName.text.toString().isEmpty()) {
                Toast.makeText(applicationContext,"Enter the name",Toast.LENGTH_SHORT).show()

            }else{
                val intent=Intent(applicationContext,QuizQuestionActivity::class.java)

                // to close the current activity
                intent.putExtra(Constant.USERNAME,etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}