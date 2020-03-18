package com.example.mulheresag.domain.user

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.BaseCallBack

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

    fun login( listener:BaseCallBack<UserModel>){
        repository.login(email, password, object : BaseCallBack<UserModel> {
            override fun onSuccessful(value: UserModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
               listener.onUnsuccessful(error)
            }

        })
    }
}