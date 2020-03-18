package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.LoginModel
import retrofit2.Call
import retrofit2.http.Field

open interface AuthAPI {
    fun login(@Field("grant_type") grantType: String,
                 @Field("email") email: String,
                 @Field("passowrd") passowrd: String): Call<LoginModel>
}