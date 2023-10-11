package com.esslinger.msu.geoquizhw5

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esslinger.msu.geoquizhw5.databinding.ActivityCheatBinding
import com.esslinger.msu.geoquizhw5.databinding.ActivityMainBinding

const val EXTRA_ANSWER_SHOWN = "com.esslinger.msu.geoquiz5.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.esslinger.msu.geoquiz5.answer_is_true"
class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        if (savedInstanceState != null) {
            setAnswerShownResult(savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false))
        } //Found at: Chapter 4 and previous app

        binding.showAnswerButton.setOnClickListener{
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_ANSWER_SHOWN, answerIsTrue)
    } //Found at: Chapter 4 and previous app
private fun setAnswerShownResult(isAnswerShown:Boolean) {
    val data = Intent().apply {
        putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
    }
    setResult(Activity.RESULT_OK, data)
}
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
