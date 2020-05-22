package com.example.mulheresag.domain.services

import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.infra.BaseCallBack

class ServiceContract {
    interface IRepository {

        fun getAllServices(onResult: BaseCallBack<ArrayList<ServiceModel>>)

        fun createService(service: ServiceModel, onResult: BaseCallBack<ServiceModel>)
    }
}