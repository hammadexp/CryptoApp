package com.matecho.wms.repository

class AccessTokenRepository private constructor() : BaseRepository() {
   /* fun refreshAccessToken(): RefreshTokenResponse? {
        val mApiInterface = client!!.create(ApiInterface::class.java)
        val hashMap = HashMap<String?, String?>()
        var body: RefreshTokenResponse? = null
        hashMap["accesstoken"] = AppConstants.TOKEN
        try {
            body = mApiInterface.getAuthenticationToken(hashMap)!!.execute().body()
            return body
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return body
    }

    fun loginAccessToken(): BaseResponse<LoginData?>? {
        val mApiInterface = client!!.create(ApiInterface::class.java)
        var body: BaseResponse<LoginData?>? = null
        val sharedPreference = SharedPreference(MyApplication.appContext)
        val username = sharedPreference.getStringValue(MyApplication.appContext.getResources().getString(
            R.string.user_email))
        val pass = sharedPreference.getStringValue(MyApplication.appContext.getResources().getString(R.string.user_password))
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)) {
            val userLogin = UserLogin(username, pass)
            try {
                body = mApiInterface.login(userLogin)!!.execute().body()
                return body
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else body = null
        return body
    }

    val accessToken: String?
        get() = AppConstants.TOKEN

    companion object {
        private var accessTokenRepository: AccessTokenRepository? = null

        @get:Synchronized
        val instance: AccessTokenRepository?
            get() {
                if (accessTokenRepository == null) {
                    accessTokenRepository = AccessTokenRepository()
                }
                return accessTokenRepository
            }
    }*/
}