package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

open interface AuthAPI {
    @FormUrlEncoded
    @POST("authenticate")
    fun login(@Field("grant_type") grantType: String,
                 @Field("email") email: String,
                 @Field("password") passowrd: String): Call<LoginModel>
}