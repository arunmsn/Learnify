package com.example.myapplicationlearnify.ui.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplicationlearnify.R
import com.example.myapplicationlearnify.model.Question
import com.example.myapplicationlearnify.utils.Constants

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar:ProgressBar
    private lateinit var textViewProgress:TextView
    private lateinit var textViewQuestion:TextView
    private lateinit var questionImage:ImageView
    private lateinit var textViewOptionOne:TextView
    private lateinit var textViewOptionTwo:TextView
    private lateinit var textViewOptionThree:TextView
    private lateinit var textViewOptionFour:TextView
    private val currentPosition = 1
    private var questionCounter = 0
    private lateinit var questionsList:MutableList<Question>
    private lateinit var checkButton:Button
    private var selectedOptionPosition = 0
    private var selectedAnswer = 0
    private lateinit var currentQuestion:Question
    private var answered = false
    private lateinit var name:String
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        questionImage = findViewById(R.id.image_question)
        textViewOptionOne = findViewById(R.id.text_view_optionOne)
        textViewOptionTwo = findViewById(R.id.text_view_optionTwo)
        textViewOptionThree = findViewById(R.id.text_view_optionThree)
        textViewOptionFour = findViewById(R.id.text_view_optionFour)

        checkButton = findViewById(R.id.button_check)

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)


        questionsList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")

        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            name = intent.getStringExtra(Constants.USER_NAME)!! //makes non-nullable
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showNextQuestion() {

        if (questionCounter < questionsList.size) {
            checkButton.text = "CHECK ANSWER"
            currentQuestion = questionsList[questionCounter]

            resetOptions()
            val question = questionsList[questionCounter]
            questionImage.setImageResource(question.image)
            progressBar.progress = questionCounter
            textViewProgress.text = "${questionCounter + 1}/${progressBar.max}"
            textViewQuestion.text = question.question
            textViewOptionOne.text = question.optionOne
            textViewOptionTwo.text = question.optionTwo
            textViewOptionThree.text = question.optionThree
            textViewOptionFour.text = question.optionFour
        } else {
            checkButton.text = "FINISH"
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)

                startActivity(it)
            }
        }

        questionCounter++
        answered = false // because we have to reset options for the next question
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOptionOne)
        options.add(textViewOptionTwo)
        options.add(textViewOptionThree)
        options.add(textViewOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_view_optionOne -> {
                selectedOption(textViewOptionOne,1)
            }
            R.id.text_view_optionTwo -> {
                selectedOption(textViewOptionTwo,2)
            }
            R.id.text_view_optionThree -> {
                selectedOption(textViewOptionThree,3)
            }
            R.id.text_view_optionFour -> {
                selectedOption(textViewOptionFour,4)
            }
            R.id.button_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }

        }
    }

    private fun selectedOption(textView:TextView, selectedOptionNumber:Int) {
        resetOptions()

        selectedOptionPosition = selectedOptionNumber
        //selectedAnswer = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun checkAnswer() {
        answered = true
        if (selectedAnswer == currentQuestion.correctAnswer) {
            highlightAnswer(selectedAnswer, currentQuestion.correctAnswer)
        } else {
            when(selectedAnswer) {
                1 -> {
                    textViewOptionOne.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
                2 -> {
                    textViewOptionTwo.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
                3 -> {
                    textViewOptionThree.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
                4 -> {
                    textViewOptionFour.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
            }
        }
        checkButton.text = "NEXT"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highlightAnswer(selectedAnswer, currentQuestion.correctAnswer)
    }

    private fun highlightAnswer(answer:Int, correctAnswer:Int) {
        if (answer == correctAnswer) {
            // highlight the correct answer and increment score
            when (answer) {
                1 -> {
                    textViewOptionOne.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                    score++ // use the global variable
                }
                2 -> {
                    textViewOptionTwo.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                    score++ // use the global variable
                }
                3 -> {
                    textViewOptionThree.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                    score++ // use the global variable
                }
                4 -> {
                    textViewOptionFour.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                    score++ // use the global variable
                }
            }
        } else {
            // highlight the wrong answer and the correct answer
            when (answer) {
                1 -> {
                    textViewOptionOne.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
                2 -> {
                    textViewOptionTwo.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
                3 -> {
                    textViewOptionThree.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
                4 -> {
                    textViewOptionFour.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
            }
            when (correctAnswer) {
                1 -> {
                    textViewOptionOne.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                }
                2 -> {
                    textViewOptionTwo.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                }
                3 -> {
                    textViewOptionThree.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                }
                4 -> {
                    textViewOptionFour.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                }
            }
        }
    }

}
