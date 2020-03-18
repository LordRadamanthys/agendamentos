package com.example.mulheresag.data.repository

import com.example.mulheresag.data.remote.AuthAPI
import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.domain.user.UserContract
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository:Repository(), UserContract.IRepository {

    override fun login(username: String, password: String, onResult: BaseCallBack<UserModel>) {
        super.data.restApi(AuthAPI::class.java)
            .login("password",username,password)
            .enqueue(object : Callback<LoginModel> {
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    if(!response.isSuccessful) return onResult.onUnsuccessful("errou")
                }

            })
    }


}