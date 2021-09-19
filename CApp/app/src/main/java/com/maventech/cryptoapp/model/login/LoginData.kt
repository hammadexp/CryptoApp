package com.matecho.wms.model.login

data class LoginData(
    var emailAddress:String,
    var client:String,
    var customerId:Int,
    var fullName:String,
    var token:String,
    var timezone:Int

)