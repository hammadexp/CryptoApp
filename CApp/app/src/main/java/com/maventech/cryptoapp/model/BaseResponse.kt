package com.matecho.wms.model

open class BaseResponse<T> (var status:Int=0){

    var isError = 0
    var message: String? = null
    open var data: T? = null
        set

}