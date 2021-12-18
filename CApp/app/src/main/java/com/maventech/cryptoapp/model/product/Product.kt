package com.maventech.cryptoapp.model.product

data class Product(
    val id:Int,
    val title:String,
    val price:String,
    val category:String,
    val description:String,
    val image:String
)
