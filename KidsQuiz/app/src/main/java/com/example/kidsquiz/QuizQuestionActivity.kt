package com.example.kidsquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {


    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedPosition: Int = 0
    private var mCorrectAnswer: Int = 0


    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constant.USERNAME)


        // object constant is available everywhere
        mQuestionList = Constant.getQuestions()
//        Log.i("Question size","${questionList.size}")

        setQuesiton()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)

        btnSubmit.setOnClickListener(this)

    }


    private fun setQuesiton() {

        // mCurrentPosition = 1
        val question: Question? = mQuestionList!![mCurrentPosition - 1]

        defaultOptionView()

        if (mCurrentPosition == mQuestionList!!.size) {
            btnSubmit.text = "Finish"
        } else {
            btnSubmit.text = "Submit"
        }

        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max

        tvQuestionId.text = question!!.question
        ivImage.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            // type face , in which text is displayed
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tvOptionThree -> {
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.tvOptionFour -> {
                selectedOptionView(tvOptionFour, 4)
            }


            R.id.btnSubmit -> {
                if (mSelectedPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuesiton()
                        }
                        else -> {
//                            Toast.makeText(
//                                this,
//                                "Successfully Completed the Test",
//                                Toast.LENGTH_SHORT
//                            ).show()

                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constant.USERNAME, mUserName)
                            intent.putExtra(Constant.CORRECT_ANSWER, mCorrectAnswer)
                            intent.putExtra(Constant.TOTAL_QUESTION, mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctOption != mSelectedPosition) {
                        answerView(mSelectedPosition, R.drawable.wrong_border_bg)
                    } else {
                        mCorrectAnswer++
                    }
                    answerView(question.correctOption, R.drawable.correct_border_bg)
                    if (mCurrentPosition == mQuestionList!!.size) {
                        btnSubmit.text = "Finish"
                    } else {
                        btnSubmit.text = "Go To Next Question"
                    }
                    mSelectedPosition = 0
                }

            }

        }

    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363a43"))
        // type face , in which text is displayed to be bold
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

}