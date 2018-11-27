package com.newapptest.manuelperera.newapptest.presentation.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.show
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

    protected fun onLoading(visible: Boolean) {
        val progressBar = findViewById<ProgressBar?>(R.id.progressBar)
        progressBar?.show(visible)
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