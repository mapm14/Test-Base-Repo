package com.newapptest.manuelperera.newapptest.presentation.main

import android.os.Bundle
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.observe
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.viewModel
import com.newapptest.manuelperera.newapptest.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var vm: MainViewModel

    override var activityLayout: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = viewModel(viewModelFactory) {
            observe(loginSuccess) { onLoginSuccess() }
            observe(failure) { onLoginFailure() }
            login()
        }
    }

    private fun onLoginSuccess() {
        loginTxtView.text = "Login Success!!"
    }

    private fun onLoginFailure() {
        loginTxtView.text = "Login Failure!!"
    }

}