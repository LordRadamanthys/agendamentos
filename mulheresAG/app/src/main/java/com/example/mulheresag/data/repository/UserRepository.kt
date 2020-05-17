package com.example.mulheresag.data.repository

import com.example.mulheresag.data.remote.UserAPI
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.domain.user.UserContract
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository
import com.example.mulheresag.infra.formatResponseError
import org.json.JSONObject
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
                    if (response.code() == 400) {
                        var value = formatResponseError(response.errorBody()?.string())
                        onResult.onUnsuccessful(value.getString("erro"))
                    }


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
                    if (response.code() == 400) {
                        var erro = formatResponseError(response.errorBody()?.string())
                        onResult.onUnsuccessful(erro.getString("error"))
                    }
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

    override fun getUser(id: Int, onResult: BaseCallBack<UserModel>) {
        super.data.restApi(UserAPI::class.java)
            .getUser(id, App.userToken)
            .enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

    override fun getAllUser( onResult: BaseCallBack<ArrayList<UserModel>>) {
        super.data.restApi(UserAPI::class.java)
            .getAllUser(App.userToken)
            .enqueue(object :Callback<ArrayList<UserModel>>{
                override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                    onResult.onUnsuccessful(t.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<UserModel>>,
                    response: Response<ArrayList<UserModel>>
                ) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }


}