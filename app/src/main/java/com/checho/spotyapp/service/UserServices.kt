package com.checho.spotyapp.service

import com.checho.spotyapp.models.UserModel
import retrofit2.http.Body
import retrofit2.http.POST

interface UserServices {

    @POST("users")
    fun createUser(@Body user: UserModel): retrofit2.Call<UserModel>
}