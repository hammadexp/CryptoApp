package com.matecho.wms.utils

import com.matecho.wms.service.ApiClient.client
import com.maventech.cryptoapp.model.APIError
import retrofit2.Response
import java.io.IOException

object ErrorUtils {
    fun parseError(response: Response<*>): APIError {
        val converter = client!!
                .responseBodyConverter<APIError>(APIError::class.java, arrayOfNulls(0))
        val error: APIError
        error = try {
            converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }
}