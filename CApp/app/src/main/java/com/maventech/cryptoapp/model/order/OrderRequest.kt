package com.maventech.cryptoapp.model.order

data class OrderRequest(
    val topic: String,
    val detail: String,
    val attachment: String,
    val educationalLevel: String,
    val consultancy: String,
    val deadline: Int,
    val standards: String,
    val pagenumber: String,
    val discount: String?,
    val discountPrice: Int,
    val totalPrice: Double,
    val paidPrice: Int,
    val customerID: Int,
    val orderID: Int
)