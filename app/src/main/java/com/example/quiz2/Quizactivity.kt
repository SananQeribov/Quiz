package com.example.quiz2

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.quiz2.databinding.ActivityQuizactivityBinding
import java.util.Timer
import java.util.TimerTask

class Quizactivity : AppCompatActivity() {
    private lateinit var questions: TextView
    private lateinit var question: TextView
    private lateinit var option1: AppCompatButton
    private lateinit var option2: AppCompatButton
    private lateinit var option3: AppCompatButton
    private lateinit var option4: AppCompatButton
    private lateinit var quiztimer: Timer
    private var totaltimemins = 1
    private var seconds = 0
    var correctAnswersCount = 0

    private lateinit var binding: ActivityQuizactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getselectedname = intent.getStringExtra("selectTopic")

        val backbt = binding.backbt
        val timer = binding.timer
        val select = binding.topicname
        questions = binding.questions
        question = binding.question
        option1 = binding.option1
        option2 = binding.option2
        option3 = binding.option3
        option4 = binding.option4
        val nextbtn = binding.nextbtn

        select.setText(getselectedname)
        starttimer(timer)
        displayquestion()
        nextbtn.setOnClickListener {
            var selectedOptionText = option3.text.toString()
            val currentQuestion = list[currentQuestionIndex]
            // Və ya seçilmiş variantın mətninə uyğun olaraq
            if (checkAnswer(selectedOptionText)) {
                correctAnswersCount++
            }


            nextquestion()
        }

        option1.setOnClickListener {
            val selectedOptionText = option1.text.toString()
            if (checkAnswer(selectedOptionText)) {
                // Doğru cavab
                // Burada əlavə tədbirlər edə bilərsiniz, məsələn, növbəti suala keçid etmək
                nextquestion()
            }
        }
        option2.setOnClickListener {
            val selectedOptionText = "Mars"
            if (checkAnswer(selectedOptionText)) {
                // Doğru cavab
                // Burada əlavə tədbirlər edə bilərsiniz, məsələn, növbəti suala keçid etmək
                nextquestion()
            }
        }
        option3.setOnClickListener {
            val selectedOptionText = option3.text.toString()
            if (checkAnswer(selectedOptionText)) {

                nextquestion()
            }
        }
        option4.setOnClickListener {
            val selectedOptionText = option4.text.toString()
            if (checkAnswer(selectedOptionText)) {
                // Doğru cavab
                // Burada əlavə tədbirlər edə bilərsiniz, məsələn, növbəti suala keçid etmək
                nextquestion()
            }
        }


        backbt?.setOnClickListener {
            quiztimer.purge()
            quiztimer.cancel()


        }
    }

    fun starttimer(timertextview: TextView) {
        quiztimer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                if (seconds == 0 && totaltimemins == 0) {

                    quiztimer.purge()
                    quiztimer.cancel()

                    runOnUiThread {
                        Toast.makeText(this@Quizactivity, "Timer over", Toast.LENGTH_SHORT).show()
                    }

                    finish()
                } else if (seconds == 0) {
                    totaltimemins--
                    seconds = 59

                } else {
                    seconds--
                }
                val myRunnable = object : Runnable {
                    override fun run() {
                        var finalminutes = totaltimemins.toString()
                        var finalseconds = seconds.toString()
                        if (finalminutes.length == 1) finalminutes = "0" + finalminutes
                        if (finalseconds.length == 1) finalseconds = "0" + finalseconds
                        timertextview.text = "$finalminutes:$finalseconds"
                    }
                }
                runOnUiThread(myRunnable)
            }
        }
        quiztimer.scheduleAtFixedRate(task, 0, 1000)
    }


    var currentQuestionIndex = 0 //

    fun displayquestion() {
        val current = list[currentQuestionIndex]
        binding.question.text = current.question
        Toast.makeText(this, "${current.question}", Toast.LENGTH_SHORT).show()
        binding.option1.text = current.countries[0]
        binding.option2.text = current.countries[1]
        binding.option3.text = current.countries[2]
        binding.option4.text = current.countries[3]

    }

    fun nextquestion() {
        currentQuestionIndex++
            if (currentQuestionIndex <= list.size) {

                binding.questions.text = "$currentQuestionIndex/4"
                displayquestion()
                //showResultDialog()
            } else {
                Toast.makeText(this, "Please choose the correct answer", Toast.LENGTH_SHORT).show()
            }

    }


    private fun checkAnswer(selectedOptionText: String): Boolean {
        val currentQuestion = list[currentQuestionIndex]
        return selectedOptionText == currentQuestion.country
    }

 //   private fun checkAnswer(selectedOptionText: String, correctAnswer: String): Boolean {
        //return selectedOptionText == correctAnswer
   // }


    private fun showResultDialog() {
        val incorrectAnswersCount = list.size - correctAnswersCount
        val message = "Correct answers: $correctAnswersCount\nIncorrect answers: $incorrectAnswersCount"
        AlertDialog.Builder(this)
            .setTitle("Quiz Result")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .setCancelable(false)
            .show()
    }
}




