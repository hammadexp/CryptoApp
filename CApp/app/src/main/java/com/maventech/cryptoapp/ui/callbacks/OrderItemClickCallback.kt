package com.maventech.cryptoapp.ui.callbacks

import com.maventech.cryptoapp.model.order.OrderItem

interface OrderItemClickCallback {
    fun onClick(response: OrderItem)

}