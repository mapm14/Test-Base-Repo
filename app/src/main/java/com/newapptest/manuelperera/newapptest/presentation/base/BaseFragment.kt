package com.newapptest.manuelperera.newapptest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.show
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @set:LayoutRes
    abstract var fragmentLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(fragmentLayout, container, false)

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if (item?.itemId == android.R.id.home) {
//            onBackPressed()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    protected fun onLoading(visible: Boolean) {
        val progressBar = view?.findViewById<ProgressBar?>(R.id.progressBar)
        progressBar?.show(visible)
    }

}