package com.example.mulheresag.domain.user

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.BaseCallBack
import okhttp3.MultipartBody

class UserContract {
    interface IRepository {

        fun login(username: String, password: String, onResult: BaseCallBack<UserModel>)

        fun createUser(user: UserModel, onResult: BaseCallBack<UserModel>)

        fun getUser(id: Int, onResult: BaseCallBack<UserModel>)

        fun getAllUser(onResult: BaseCallBack<ArrayList<UserModel>>)

        fun uploadPhoto(file:MultipartBody.Part,token:String,onResult: BaseCallBack<String>)
    }
}