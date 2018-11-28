package com.newapptest.manuelperera.newapptest.presentation.orders

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment() {

    override var fragmentLayout: Int = R.layout.fragment_orders

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderDetailButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_next)
        }
    }

}