package com.matecho.wms.service

import com.matecho.wms.repository.AccessTokenRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class TokenAuthenticator(private val accessTokenRepository: AccessTokenRepository) : Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
    /*    val tokenResponse = accessTokenRepository.loginAccessToken()
        // Add new header to rejected request and retry it
        return if (tokenResponse != null && tokenResponse.data != null && !TextUtils.isEmpty(tokenResponse.data!!.userData.courierName)) {
            if (MyApplication.appContext != null) {
//                Helper.saveToken(MyApplication.getAppContext(), tokenResponse.getData().getToken());
                Helper.loadToken(MyApplication.appContext)
            }
            response.request().newBuilder()
                    .header(AppConstants.AUTHORIZATION, AppConstants.TOKEN)
                    .build()
        } else null*/
        return null;
    }

    private fun isRequestWithAccessToken(response: Response): Boolean {
        val header = response.request.header("Authorization")
        return header != null && header.startsWith("Bearer")
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
    }



}