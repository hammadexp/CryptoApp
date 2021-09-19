package com.matecho.wms.repository

import com.matecho.wms.db.ClientsDao
import com.matecho.wms.model.UserLogin
import com.matecho.wms.model.UserRegister
import com.matecho.wms.model.educationLevel.EducationLevel
import com.matecho.wms.service.ApiInterface
import javax.inject.Inject

class LoginRepository @Inject constructor(
    val clientsDao: ClientsDao,val apiInterface: ApiInterface
) : BaseRepository() {

    /**
     * Api Calling
     */


    suspend fun login(login: UserLogin): Any {
        val data =
            safeApiCall({ apiInterface.login(login).execute() }, "No response")
        return data!!
    }

    suspend fun register(login: UserRegister): Any {
        val data =
            safeApiCall({ apiInterface.register(login).execute() }, "No response")
        return data!!
    }



    /**
     * Database calling
     */

    fun insertEducationData(data: MutableList<EducationLevel>): Any {
        val result = clientsDao.insertAll(data)
        return result
    }

}