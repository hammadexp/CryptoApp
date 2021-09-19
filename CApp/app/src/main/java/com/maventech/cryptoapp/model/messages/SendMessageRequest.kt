package com.maventech.cryptoapp.model.messages

data class SendMessageRequest (
    val orderid:Int,
    val fromm:String,
    val too:String,
    val subject:String,
    val message1:String,
    var attachedfile:String?=null,
    val messagedate:String?=null,
    val messageid:Int?=null
        )