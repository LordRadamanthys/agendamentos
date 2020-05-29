package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

open interface UserAPI {
    @FormUrlEncoded
    @POST("authenticate")
    fun login(@Field("grant_type") grantType: String,
                 @Field("email") email: String,
                 @Field("password") passowrd: String): Call<UserModel>


    @POST("user/create")
    fun createUser(@Body user: UserModel): Call<UserModel>

    @PUT("user")
    fun updateUser(@Body user: UserModel, @Header("Authorization") token:String): Call<String>

    @GET("user/{id}")
    fun getUser(@Path("id") id:Int,
                   @Header("Authorization") token:String): Call<UserModel>

    @GET("users")
    fun getAllUser(@Header("Authorization") token:String): Call<ArrayList<UserModel>>

    @Multipart
    @POST("uploads")
    fun uploadPhoto(@Header("Authorization") token:String, @Part file: MultipartBody.Part):Call<String>
}