package com.esslinger.msu.geoquizhw5

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.esslinger.msu.geoquizhw5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"
private var correctAnswersCount = 0

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private val quizViewModel:QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()

    ) { result->
        if (result.resultCode == Activity.RESULT_OK) {
            val currentQuestionIndex = quizViewModel.getCurrentQuestionIndex()
            val isAnswerShown = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            quizViewModel.setCheaterForQuestion(currentQuestionIndex, isAnswerShown)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.cheatButton.setOnClickListener {
           // val intent = Intent (this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            //startActivity(intent)
            cheatLauncher.launch(intent)
        }

        binding.nextButton.setOnClickListener {
            quizViewModel.movetoNext()

            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextview.setText(questionTextResId)

    }
    private fun checkAnswer(userAnswer: Boolean) {
        val currentQuestionIndex = quizViewModel.getCurrentQuestionIndex()
        val currentQuestionAnswer = quizViewModel.currentQuestionAnswer

        if (quizViewModel.isCheaterForQuestion(currentQuestionIndex)) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show()
        } else {
            val messageResId = when {
                userAnswer == currentQuestionAnswer -> R.string.correct_toast
                else -> R.string.incorrect_toast
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
        }
    } //reference: chat GPT

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
