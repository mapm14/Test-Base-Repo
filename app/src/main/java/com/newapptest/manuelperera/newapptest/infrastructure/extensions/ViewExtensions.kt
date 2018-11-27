package com.newapptest.manuelperera.newapptest.infrastructure.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.show(visible: Boolean) {
    visibility = when (visible) {
        true -> VISIBLE
        false -> GONE
    }
}

fun View.gone() {
    visibility = GONE
}

fun View.visible() {
    visibility = VISIBLE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun View.snackbar(
        title: String = "",
        action: String = "",
        length: Int = Snackbar.LENGTH_LONG,
        @ColorRes actionColor: Int = android.R.color.white,
        actionResult: () -> Unit = {}
) {

    val snackbar = Snackbar.make(this, title, length)

    if (action.isNotEmpty()) {
        snackbar.setAction(action) { actionResult() }
        snackbar.setActionTextColor(ContextCompat.getColor(context, actionColor))
    }
    snackbar.show()
}

fun View.snackbar(
        @StringRes titleRes: Int = 0,
        @StringRes actionRes: Int = 0,
        length: Int = Snackbar.LENGTH_LONG,
        @ColorRes actionColor: Int = android.R.color.white,
        actionResult: () -> Unit = {}
) {

    snackbar(context.getString(titleRes), context.getString(actionRes), length, actionColor, actionResult)

}