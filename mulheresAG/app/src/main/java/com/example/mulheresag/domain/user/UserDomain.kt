package com.example.mulheresag.domain.user

import android.content.Intent
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.Exception

class UserDomain(email: String, password: String) {

    lateinit var repository: UserContract.IRepository
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String
    var admin: Boolean = false
    lateinit var token: String


    init {
        this.email = email
        this.password = password
    }

    fun setUserToken(newToken: String) {
        token = "Bearer $newToken"
    }


    fun login(listener: BaseCallBack<UserModel>) {

        if (email.length < 4 || password.length < 1) throw Exception("email ou senha não podem ser vazios")

        repository.login(email, password, object : BaseCallBack<UserModel> {

            override fun onSuccessful(value: UserModel) {
                setUserToken(value.token)
                App.userToken = token
                App.userName = value.name
                App.user = value
                App.isAdmin = value.admin
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })

    }

    fun getAllusers(listenner: BaseCallBack<ArrayList<UserModel>>) {
        repository.getAllUser(object : BaseCallBack<ArrayList<UserModel>> {
            override fun onSuccessful(value: ArrayList<UserModel>) {
                listenner.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listenner.onUnsuccessful(error)
            }
        })
    }

    fun uploadPhoto(file: MultipartBody.Part, token: String, listener: BaseCallBack<String>) {
        repository.uploadPhoto(file, token, object : BaseCallBack<String> {
            override fun onSuccessful(value: String) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }
        })
    }

    fun uploadUser(user: UserModel, listener: BaseCallBack<UserModel>) {
        repository.updateUser(user, object : BaseCallBack<UserModel> {
            override fun onSuccessful(value: UserModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }
}