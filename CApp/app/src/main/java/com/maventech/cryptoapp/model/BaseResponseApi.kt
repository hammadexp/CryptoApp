package com.matecho.wms.model

open class BaseResponseApi<T> (){

    var statusCode = 0
    var message: String? = null
    open var result: T? = null
        set

}