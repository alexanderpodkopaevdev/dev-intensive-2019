package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager



fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.isKeyboardOpen() : Boolean {
    val screenHeight: Int = this.window.decorView.rootView.height
    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)
    val visibleDisplayFrameHeight: Int = rect.top
    val diff = (screenHeight - visibleDisplayFrameHeight)
    return diff > 100
}

fun Activity.isKeyboardClosed() : Boolean  {
    val screenHeight: Int = this.window.decorView.rootView.height
    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)
    val visibleDisplayFrameHeight: Int = rect.top
    val diff = (screenHeight - visibleDisplayFrameHeight)
    return diff < 100
}
