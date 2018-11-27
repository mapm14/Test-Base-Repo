package com.newapptest.manuelperera.newapptest.presentation.main

import android.os.Bundle
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.observe
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.observeFailure
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.viewModel
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.visible
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
            observe(ldLogin) { onLoginSuccess() }
            observe(ldLoading) {
                if (it == false) loginTxtView.visible()
                onLoading(it ?: false)
            }
            observeFailure(ldFailure) { onLoginFailure(it) }
            login()
        }
    }

    private fun onLoginSuccess() {
        loginTxtView.text = getString(R.string.login_success)
    }

    private fun onLoginFailure(failure: Failure) {
        loginTxtView.text = when (failure) {
            is Failure.Error -> failure.msg
            is Failure.Timeout -> failure.msg
            else -> getString(R.string.unknown_error)
        }
    }

}