package com.newapptest.manuelperera.newapptest.presentation.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @set:LayoutRes
    abstract var activityLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun showToast(message: String, resId: Int) {
        if (resId == -1) Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        else Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()
    }

    protected fun showSnackbarWithRes(title: Int, action: Int, length: Int, actionResult: () -> Unit) {
        val container = findViewById<View?>(R.id.parentContainer)
        container?.snackbar(title, action, length, actionResult = actionResult)
    }

    protected fun showSnackbar(title: String, action: String, length: Int, actionResult: () -> Unit) {
        val container = findViewById<View?>(R.id.parentContainer)
        container?.snackbar(title, action, length, actionResult = actionResult)
    }

}