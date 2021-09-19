package com.maventech.cryptoapp.model.products

import com.google.gson.annotations.SerializedName

data class Data (

    @SerializedName("product_image_sm") val product_image_sm : String,
    @SerializedName("product_image_md") val product_image_md : String,
    @SerializedName("product_image_lg") val product_image_lg : String,
    @SerializedName("_id") val _id : String,
    @SerializedName("product_name") val product_name : String,
    @SerializedName("product_type") val product_type : String,
    @SerializedName("product_department") val product_department : String,
    @SerializedName("product_departmentId") val product_departmentId : String,
    @SerializedName("product_stock") val product_stock : Int,
    @SerializedName("product_color") val product_color : String,
    @SerializedName("product_price") val product_price : Int,
    @SerializedName("product_material") val product_material : String,
    @SerializedName("product_ratings") val product_ratings : Int,
    @SerializedName("product_sales") val product_sales : Int,
    @SerializedName("__v") val __v : Int
)