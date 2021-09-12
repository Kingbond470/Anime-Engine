package com.example.kidsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName=intent.getStringExtra(Constant.USERNAME)
        tvName.text=userName

        val totalQuestion=intent.getIntExtra(Constant.TOTAL_QUESTION,0)
        val correctAnswer=intent.getIntExtra(Constant.CORRECT_ANSWER,0)

        tvScore.text="Your Score is $correctAnswer out of $totalQuestion"

        btnFinish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}