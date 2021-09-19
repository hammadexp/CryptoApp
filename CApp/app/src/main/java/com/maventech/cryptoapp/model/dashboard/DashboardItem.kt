package com.maventech.cryptoapp.model.dashboard

data class DashboardItem(
    val key:String,
    val value:Int,
    val colorCode:String?=null,
    val id:Int
)
