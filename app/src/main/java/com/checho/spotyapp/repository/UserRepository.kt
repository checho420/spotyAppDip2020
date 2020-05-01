package com.checho.spotyapp.repository

import com.checho.spotyapp.models.*
import com.checho.spotyapp.service.ServiceFactory
import com.checho.spotyapp.service.SpotyServices
import com.checho.spotyapp.service.UserServices
import retrofit2.Call
import java.lang.Exception

class UserRepository {

    private var userServices: UserServices

    init {
        val serviceFactory = ServiceFactory()
        userServices = serviceFactory.getInstatceUserService()
    }

    fun createUser(user: UserModel): UserModel {
        try {
            val call: retrofit2.Call<UserModel> = userServices.createUser(user)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (exception: Exception) {
            throw exception
        }
    }




}