package com.example.mulheresag.domain.reservation

import com.example.mulheresag.data.remote.model.CreateReservationModel
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.infra.BaseCallBack

class ReservationContract {
    interface IRepository {

        fun getReservation(onResult: BaseCallBack<ArrayList< ReservationModel>>)

        fun createReservation(model:ReservationModel, onResult: BaseCallBack<ReservationModel>)
//        fun getReservation(onResult: BaseCallBack<ArrayList< ReservationModel>>)


    }
}