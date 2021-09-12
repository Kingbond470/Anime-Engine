package com.example.kidsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // object contant is available everywhere
        val questionList=Constant.getQuestions()
        Log.i("Question size","${questionList.size}")
    }
}