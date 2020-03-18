package com.example.mulheresag.domain.user

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.BaseCallBack
import java.lang.Exception

class UserDomain(email: String, password: String) {
    lateinit var repository:UserContract.IRepository
    lateinit var name:String
    lateinit var email:String
    lateinit var password:String
    var admin:Boolean = false
    lateinit var token:String
    init {
        this.email = email
        this.password = password
    }

    fun login( listener:BaseCallBack<LoginModel>){
        if(email.length<4 || password.length<1)  throw Exception("email ou senha não podem ser vazios")
        repository.login(email, password, object : BaseCallBack<LoginModel> {
            override fun onSuccessful(value: LoginModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
               listener.onUnsuccessful(error)
            }

        })
    }
}