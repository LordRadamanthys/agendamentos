package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import retrofit2.Call
import retrofit2.http.*

open interface UserAPI {
    @FormUrlEncoded
    @POST("authenticate")
    fun login(@Field("grant_type") grantType: String,
                 @Field("email") email: String,
                 @Field("password") passowrd: String): Call<UserModel>


    @POST("cadUser")
    fun createUser(@Body user: UserModel): Call<UserModel>

    @GET("getUser")
    fun getUser(@Body id: Int,
                   @Header("Authorization") token:String): Call<UserModel>
}