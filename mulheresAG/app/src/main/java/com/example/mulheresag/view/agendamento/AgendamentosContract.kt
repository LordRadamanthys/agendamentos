package com.example.mulheresag.view.agendamento

import com.example.mulheresag.data.remote.model.CreateReservationModel
import com.example.mulheresag.data.remote.model.ReservationModel

class AgendamentosContract {
    interface View{
        fun showAlert(text: String , key:Boolean)
    }


    interface Presenter{
        fun createReservation(model: ReservationModel)

    }
}