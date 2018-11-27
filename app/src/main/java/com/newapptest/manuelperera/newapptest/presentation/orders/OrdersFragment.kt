package com.newapptest.manuelperera.newapptest.presentation.orders

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.presentation.base.BaseFragment
import com.newapptest.manuelperera.newapptest.presentation.orderdetail.OrderDetailFragment
import kotlinx.android.synthetic.main.fragment_orders.*
import java.io.Serializable
import java.util.*

class OrdersFragment : BaseFragment() {

    override var fragmentLayout: Int = R.layout.fragment_orders

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderDetailButton.setOnClickListener {
//            val actionNext = OrdersFragmentDirections.actionNext()
//            actionNext.setPrice()

            val pricePoint = PricePoint("2,00", "", 1)
            val bundle = OrderDetailFragment.getBundle(pricePoint)

            Navigation.findNavController(it).navigate(R.id.action_next, bundle)
        }
    }

    class PricePoint(
            val value: String,
            val currency: String,
            val x: Int
    ) : Serializable

}