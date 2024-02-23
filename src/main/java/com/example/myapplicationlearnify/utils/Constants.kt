package com.example.myapplicationlearnify.utils

import com.example.myapplicationlearnify.R
import com.example.myapplicationlearnify.model.Question

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE = "correct_answers"

    fun getQuestions():MutableList<Question> {
        val questions = mutableListOf<Question>()
        val quest1 = Question (
            1,
            "Which is the correct answer?",
            R.drawable.learnifyq1,
            "c+d",
            "2c+d",
            "2c+2d",
            "2c-2d",
            3
        )
        val quest2 = Question (
            2,
            "Which is the correct answer?",
            R.drawable.learnifyq2,
            "10m",
            "6m+4",
            "6m+6",
            "6m-4",
            2
        )
        val quest3 = Question (
            3,
            "Which is the correct answer?",
            R.drawable.learnifyq3,
            "8a-2b",
            "12a-b",
            "12a+5b",
            "10a+b",
            2
        )
        val quest4 = Question (
            4,
            "Which is the correct answer?",
            R.drawable.learnifyq4,
            "$24",
            "$67",
            "$48",
            "$27",
            3
        )
        val quest5 = Question (
            5,
            "Which is the correct answer?",
            R.drawable.learnifyq5,
            "4x,6x",
            "4,x-6",
            "2,2x-3",
            "2x,2x-3",
            3
        )
        val quest6 = Question (
            6,
            "Which is the correct answer?",
            R.drawable.learnifyq6,
            "C=5(F-32)/9",
            "C=(5F-32)/9",
            "(5F/9)-32",
            "5F-(32/9)",
            1
        )
        val quest7 = Question (
            7,
            "Which is the correct answer?",
            R.drawable.learnifyq7,
            "20 degrees",
            "26 degrees",
            "8 degrees",
            "34 degrees",
            4
        )
        val quest8 = Question (
            8,
            "Which is the correct answer?",
            R.drawable.learnifyq8,
            "70",
            "42",
            "22",
            "35",
            4
        )
        val quest9 = Question (
            9,
            "Which is the correct answer?",
            R.drawable.learnifyq9,
            "y=2x-1",
            "y=2x+1",
            "y=4x-2",
            "y=2x+5",
            2
        )
        questions.add(quest1)
        questions.add(quest2)
        questions.add(quest3)
        questions.add(quest4)
        questions.add(quest5)
        questions.add(quest6)
        questions.add(quest7)
        questions.add(quest8)
        questions.add(quest9)
        return questions
    }
}