package com.esslinger.msu.geoquizhw4

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean,  var answered: Boolean = false)