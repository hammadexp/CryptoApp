package com.maventech.cryptoapp.model.messages

import com.google.gson.annotations.SerializedName


data class Message (

	@SerializedName("customerID") var customerID : Int,
	@SerializedName("orderID") var orderID : Int,
	@SerializedName("loginId") var loginId : String,
	@SerializedName("password") var password : String,
	@SerializedName("secondaryEmail") var secondaryEmail : String,
	@SerializedName("name") var name : String,
	@SerializedName("phoneNumber") var phoneNumber : String,
	@SerializedName("countryID") var countryID : Int,
	@SerializedName("country") var country : String,
	@SerializedName("timezoneID") var timezoneID : String,
	@SerializedName("timeZone") var timeZone : String,
	@SerializedName("timeZoneHours") var timeZoneHours : String,
	@SerializedName("educationLevelID") var educationLevelID : Int,
	@SerializedName("educationalLevel") var educationalLevel : String,
	@SerializedName("productID") var productID : Int,
	@SerializedName("product") var product : String,
	@SerializedName("deadlineID") var deadlineID : Int,
	@SerializedName("deadline") var deadline : String,
	@SerializedName("standardID") var standardID : Int,
	@SerializedName("standard") var standard : String,
	@SerializedName("numberofPages") var numberofPages : Int,
	@SerializedName("subject") var subject : String,
	@SerializedName("topic") var topic : String,
	@SerializedName("instructions") var instructions : String,
	@SerializedName("styleofWork") var styleofWork : String,
	@SerializedName("language") var language : String,
	@SerializedName("requiredRefrences") var requiredRefrences : String,
	@SerializedName("fontType") var fontType : String,
	@SerializedName("sapcing") var sapcing : String,
	@SerializedName("attachedFile") var attachedFile : String,
	@SerializedName("totalPrice") var totalPrice : Double,
	@SerializedName("paidPrice") var paidPrice : Double,
	@SerializedName("discount") var discount : Int,
	@SerializedName("discountPercentage") var discountPercentage : String,
	@SerializedName("discountCode") var discountCode : String,
	@SerializedName("orderDate") var orderDate : String,
	@SerializedName("orderDilveryDate") var orderDilveryDate : String,
	@SerializedName("refrenceUser") var refrenceUser : String,
	@SerializedName("site") var site : String,
	@SerializedName("paymentID") var paymentID : String,
	@SerializedName("noofWords") var noofWords : Int
)