package com.example.mulheresag.infra

import com.example.mulheresag.data.remote.model.UserModel

data class LocalData(val t:String) {
    

    lateinit var user : UserModel
    fun putPreferences(){}
}