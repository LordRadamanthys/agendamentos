package com.example.mulheresag.data.remote.model

import com.google.gson.annotations.SerializedName


class UserModel {
    var id=-1
    lateinit var name:String
    lateinit var email:String
    var password:String =""
    var admin:Boolean = false
    lateinit var token:String
    @SerializedName("device")
    lateinit var tokenDevice:String
//    lateinit var error:ErrorModel


}