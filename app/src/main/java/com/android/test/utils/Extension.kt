package com.android.test.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun hideKeyboardForce(act: Activity?) {
    if (act != null) {
        val inputMethodManager =
            act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}