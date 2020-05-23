package com.example.mulheresag.domain.reservation

import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.infra.BaseCallBack

class ReservationDomain {
    lateinit var repository: ReservationContract.IRepository

    fun getListReservations(id: Int, listener: BaseCallBack<ArrayList<ReservationModel>>) {
        repository.getReservation(id, object : BaseCallBack<ArrayList<ReservationModel>> {
            override fun onSuccessful(value: ArrayList<ReservationModel>) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }


    fun createReservation(
        modelReservation: ReservationModel,
        listener: BaseCallBack<ReservationModel>
    ) {
        repository.createReservation(modelReservation, object : BaseCallBack<ReservationModel> {
            override fun onSuccessful(value: ReservationModel) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }


    fun getAllServices(listener: BaseCallBack<ArrayList<ServiceModel>>) {
        repository.getAllServices(object : BaseCallBack<ArrayList<ServiceModel>> {
            override fun onSuccessful(value: ArrayList<ServiceModel>) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }
}