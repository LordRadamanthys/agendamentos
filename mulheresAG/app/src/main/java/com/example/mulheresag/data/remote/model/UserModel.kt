package com.example.mulheresag.data.remote.model

import com.google.gson.annotations.SerializedName


class UserModel {
    lateinit var name:String
    lateinit var email:String
    lateinit var password:String
    var admin:Boolean = false
    lateinit var token:String
    @SerializedName("device")
    lateinit var tokenDevice:String


}