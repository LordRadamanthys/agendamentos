package com.example.mulheresag.data.repository

import com.example.mulheresag.data.remote.ServicesAPI
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.domain.services.ServiceContract
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesRepository :Repository(),ServiceContract.IRepository{
    override fun getAllServices(onResult: BaseCallBack<ArrayList<ServiceModel>>) {
        super.data.restApi(ServicesAPI::class.java)
            .getAllServices(App.userToken)
            .enqueue(object : Callback<ArrayList<ServiceModel>>{
                override fun onFailure(call: Call<ArrayList<ServiceModel>>, t: Throwable) {
                    onResult.onUnsuccessful(t.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<ServiceModel>>,
                    response: Response<ArrayList<ServiceModel>>
                ) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

    override fun createService(service: ServiceModel, onResult: BaseCallBack<ServiceModel>) {
        super.data.restApi(ServicesAPI::class.java)
            .createService(service,App.userToken)
            .enqueue(object :Callback<ServiceModel>{
                override fun onFailure(call: Call<ServiceModel>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ServiceModel>,
                    response: Response<ServiceModel>
                ) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

}