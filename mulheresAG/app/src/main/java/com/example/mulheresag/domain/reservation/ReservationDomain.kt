package com.example.mulheresag.domain.reservation

import com.example.mulheresag.data.remote.model.CreateReservationModel
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.infra.BaseCallBack

class ReservationDomain {
    lateinit var repository: ReservationContract.IRepository

    fun getListReservations(listener: BaseCallBack<ArrayList<ReservationModel>>) {
        repository.getReservation(object : BaseCallBack<ArrayList<ReservationModel>> {
            override fun onSuccessful(value: ArrayList<ReservationModel>) {
                listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }


    fun createReservation(modelReservation: ReservationModel, listener: BaseCallBack<ReservationModel>) {
        repository.createReservation(modelReservation, object : BaseCallBack<ReservationModel> {
                override fun onSuccessful(value: ReservationModel) {
                    listener.onSuccessful(value)
                }

                override fun onUnsuccessful(error: String) {
                    listener.onUnsuccessful(error)
                }

            })
    }
}