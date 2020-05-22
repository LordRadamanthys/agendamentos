package com.example.mulheresag.domain.services

import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.infra.BaseCallBack
import java.lang.Exception

class ServiceDomain {
lateinit var repository:ServiceContract.IRepository

    fun createService(model:ServiceModel, listener:BaseCallBack<ServiceModel>){
        if(model.title=="") throw Exception("Nome deve ser Preenchido")
        if(model.title=="") throw Exception("Descrição deve ser Preenchido")
        if(model.value<1) throw Exception("Valor deve ser Preenchido e maior que 0")

        repository.createService(model,object :BaseCallBack<ServiceModel>{
            override fun onSuccessful(value: ServiceModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }

    fun updateService(model:ServiceModel, listener:BaseCallBack<String>){
        if(model.title=="") throw Exception("Nome deve ser Preenchido")
        if(model.title=="") throw Exception("Descrição deve ser Preenchido")
        if(model.value<1) throw Exception("Valor deve ser Preenchido e maior que 0")

        repository.updateService(model,object :BaseCallBack<String>{
            override fun onSuccessful(value: String) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }



    fun getService(id:Int, listener:BaseCallBack<ServiceModel>) {
        repository.getService(id, object :BaseCallBack<ServiceModel>{
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