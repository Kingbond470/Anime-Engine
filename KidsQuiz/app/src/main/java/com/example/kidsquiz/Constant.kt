package com.example.kidsquiz

// to store data of question
object Constant {

    const val USERNAME:String="userName"
    const val TOTAL_QUESTION:String="totalQuestion"
    const val CORRECT_ANSWER:String="correctAnswer"

    // contain all the questions
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Which is your favourite animal?",
            R.drawable.tiger,
            "Lion",
            "Tiger",
            "Leopard",
            "Elephant",
            2
        )

        questionList.add(que1)

        val que2 = Question(
            2,
            "he largest living animal is the",
            R.drawable.leopard,
            "Hummingbird",
            "Ostrich",
            "Blue Whale",
            "Elephant",
            3
        )

        questionList.add(que2)

        val que3 = Question(
            3,
            "Which is your favourite animal?",
            R.drawable.tiger,
            "Lion",
            "Tiger",
            "Leopard",
            "Elephant",
            2
        )

        questionList.add(que3)

        val que4= Question(
            4,
            "he largest living animal is the",
            R.drawable.leopard,
            "Hummingbird",
            "Ostrich",
            "Blue Whale",
            "Elephant",
            3
        )

        questionList.add(que4)

        val que5 = Question(
            5,
            "Which is your favourite animal?",
            R.drawable.tiger,
            "Lion",
            "Tiger",
            "Leopard",
            "Elephant",
            2
        )

        questionList.add(que5)

        val que6 = Question(
            6,
            "he largest living animal is the",
            R.drawable.leopard,
            "Hummingbird",
            "Ostrich",
            "Blue Whale",
            "Elephant",
            3
        )

        questionList.add(que6)

        val que7 = Question(
            7,
            "Which is your favourite animal?",
            R.drawable.tiger,
            "Lion",
            "Tiger",
            "Leopard",
            "Elephant",
            2
        )

        questionList.add(que7)

        val que8 = Question(
            8,
            "he largest living animal is the",
            R.drawable.leopard,
            "Hummingbird",
            "Ostrich",
            "Blue Whale",
            "Elephant",
            3
        )

        questionList.add(que8)

        val que9 = Question(
            9,
            "Which is your favourite animal?",
            R.drawable.tiger,
            "Lion",
            "Tiger",
            "Leopard",
            "Elephant",
            2
        )

        questionList.add(que9)

        val que10 = Question(
            10,
            "he largest living animal is the",
            R.drawable.leopard,
            "Hummingbird",
            "Ostrich",
            "Blue Whale",
            "Elephant",
            3
        )

        questionList.add(que10)

        // give quiz random each time
        //Collection.shuffled(questionList)

        return questionList
    }

}