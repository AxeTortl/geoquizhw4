package com.esslinger.msu.geoquizhw4

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private  const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle:SavedStateHandle):ViewModel() {
    /*init{
        Log.d(TAG, " QuizViewModel instance created")

    }
    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "QuizViewModel instance about to be destroyed")
    }*/
    private val questionBank = mutableListOf(
        Question(R.string.question_australia, answer = true),
        Question(R.string.question_oceans, answer = true),
        Question(R.string.question_mideast, answer = false),
        Question(R.string.question_africa, answer = false),
        Question(R.string.question_americas, answer = true),
        Question(R.string.question_asia,answer = true)
    )
    private var currentIndex:Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun movetoNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}