package com.maventech.cryptoapp.model

import com.matecho.wms.model.BaseResponse

class RefreshTokenResponse : BaseResponse<String>() {
    override var data: String? = null

}