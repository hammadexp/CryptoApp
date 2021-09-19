package com.maventech.cryptoapp.model.products

import com.google.gson.annotations.SerializedName


data class ProductListResponse (

    @SerializedName("success") val success : Boolean,
    @SerializedName("datatype") val datatype : String,
    @SerializedName("numOfResults") val numOfResults : Int,
    @SerializedName("lastPage") val lastPage : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("data") val data : List<Data>
)