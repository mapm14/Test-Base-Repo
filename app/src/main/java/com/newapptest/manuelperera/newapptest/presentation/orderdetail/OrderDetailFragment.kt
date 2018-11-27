package com.newapptest.manuelperera.newapptest.presentation.orderdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.presentation.base.BaseFragment
import com.newapptest.manuelperera.newapptest.presentation.orders.OrdersFragment
import kotlinx.android.synthetic.main.fragment_order_detail.*

class OrderDetailFragment : BaseFragment() {

    companion object {
        fun getBundle(pricePoint: OrdersFragment.PricePoint) = Bundle().apply {
            putSerializable("price", pricePoint)
        }
    }

    override var fragmentLayout: Int = R.layout.fragment_order_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
//            val safeArgs = OrderDetailFragmentArgs.fromBundle(it)
//            priceTxtView.text = safeArgs.price.toString() + " €"

            val pricePoint = it.getSerializable("price") as OrdersFragment.PricePoint
            priceTxtView.text = pricePoint.value + " €"
        }

        priceTxtView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_next)
        }
    }

}