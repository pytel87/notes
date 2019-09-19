package com.pytel.notes.framework.utils


import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment



fun Context.isLandscape() = Configuration.ORIENTATION_LANDSCAPE == this.resources.configuration.orientation

fun Fragment.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Fragment.hideKeyboard() {
    val view = activity?.findViewById<View>(android.R.id.content)
    view?.let {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

}

