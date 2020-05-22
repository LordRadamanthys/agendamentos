package com.example.mulheresag.domain.services

import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.infra.BaseCallBack

class ServiceDomain {
lateinit var repository:ServiceContract.IRepository

    fun createService(model:ServiceModel, listener:BaseCallBack<ServiceModel>){
        repository.createService(model,object :BaseCallBack<ServiceModel>{
            override fun onSuccessful(value: ServiceModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }


    fun getAllService(listener:BaseCallBack<ArrayList<ServiceModel>>) {
        repository.getAllServices(object :BaseCallBack<ArrayList<ServiceModel>>{
            override fun onSuccessful(value: ArrayList<ServiceModel>) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }
}