package com.example.mulheresag.view.agendamento

import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel

class AgendamentosContract {
    interface View {
        fun showAlert(text: String, key: Boolean)
        fun loadServicesList(listServices: ArrayList<ServiceModel>)
        fun showProgressBar(key: Boolean)
    }


    interface Presenter {
        fun createReservation(model: ReservationModel)
        fun getAllServoces()
    }
}