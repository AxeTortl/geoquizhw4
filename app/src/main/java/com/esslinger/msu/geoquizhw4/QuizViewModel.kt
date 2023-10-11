package com.esslinger.msu.geoquizhw5

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private  const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle:SavedStateHandle):ViewModel() {
    fun getCurrentQuestionIndex(): Int {
        return currentIndex
    } //added this to allow main activity access
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

    fun isCheaterForQuestion(index: Int): Boolean {
        return savedStateHandle.get(IS_CHEATER_KEY + index) ?: false
    }
    fun setCheaterForQuestion(index: Int, isCheater: Boolean) {
        savedStateHandle.set(IS_CHEATER_KEY + index, isCheater)
    }

    fun movetoNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}