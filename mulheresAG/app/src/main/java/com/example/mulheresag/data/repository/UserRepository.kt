package com.example.mulheresag.data.repository

import com.example.mulheresag.data.remote.UserAPI
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.domain.user.UserContract
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : Repository(), UserContract.IRepository {

    override fun login(username: String, password: String, onResult: BaseCallBack<UserModel>) {
        super.data.restApi(UserAPI::class.java)
            .login("password", username, password)
            .enqueue(object : Callback<UserModel> {

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    if (!response.isSuccessful) return onResult.onUnsuccessful("errou")
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

    override fun createUser(user: UserModel, onResult: BaseCallBack<UserModel>) {
        super.data.restApi(UserAPI::class.java)
            .createUser(user)
            .enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

    override fun getUser(id: Int, onResult: BaseCallBack<UserModel>) {
       super.data.restApi(UserAPI::class.java)
           .getUser(id,App.userToken)
           .enqueue(object : Callback<UserModel> {
               override fun onFailure(call: Call<UserModel>, t: Throwable) {
                   onResult.onUnsuccessful(t.message.toString())
               }

               override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                   response.body()?.let { onResult.onSuccessful(it) }
               }

           })
    }


}