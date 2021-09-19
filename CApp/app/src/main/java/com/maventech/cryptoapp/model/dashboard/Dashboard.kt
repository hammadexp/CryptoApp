package com.maventech.cryptoapp.model.dashboard

data class Dashboard(
    val pendingOrders:Int,
    val orderInprgoress:Int,
    val completedOrders:Int,
    val unreadMessages:Int,
    val cancelledOrders:Int,
    val taskInRevsion:Int
)
