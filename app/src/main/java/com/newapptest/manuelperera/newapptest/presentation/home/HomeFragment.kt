package com.newapptest.manuelperera.newapptest.presentation.home

import android.os.Bundle
import android.view.View
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.observe
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.observeFailure
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.viewModel
import com.newapptest.manuelperera.newapptest.infrastructure.extensions.visible
import com.newapptest.manuelperera.newapptest.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var vm: HomeViewModel

    override var fragmentLayout: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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