package com.example.mulheresag.domain.user

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import okhttp3.MultipartBody

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

        if (email.isEmpty()) throw Exception("Email ou senha não podem ser vazios")
        if (password.length < 3) throw Exception("senha deve ter mais de 3 caracteres")
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) throw Exception("Email invalido")

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

    fun getAllusers(listener: BaseCallBack<ArrayList<UserModel>>) {
        repository.getAllUser(object : BaseCallBack<ArrayList<UserModel>> {
            override fun onSuccessful(value: ArrayList<UserModel>) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }
        })
    }

    fun getUser(id: Int, listener: BaseCallBack<UserModel>) {
        repository.getUser(id, object : BaseCallBack<UserModel> {
            override fun onSuccessful(value: UserModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
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

    fun updateUser(user: UserModel, listener: BaseCallBack<String>) {
        repository.updateUser(user, object : BaseCallBack<String> {
            override fun onSuccessful(value: String) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }
}