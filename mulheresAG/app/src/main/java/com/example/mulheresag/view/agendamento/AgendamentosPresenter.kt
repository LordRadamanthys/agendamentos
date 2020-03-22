package com.example.mulheresag.view.agendamento

import com.example.mulheresag.data.remote.model.CreateReservationModel
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.repository.ReservationRepository
import com.example.mulheresag.domain.reservation.ReservationDomain
import com.example.mulheresag.infra.BaseCallBack

class AgendamentosPresenter(view: AgendamentosContract.View) : AgendamentosContract.Presenter {
    var view: AgendamentosContract.View

    init {
        this.view = view
    }

    override fun createReservation(model: ReservationModel) {
        var domain = ReservationDomain()
        domain.repository = ReservationRepository()
        domain.createReservation(model, object : BaseCallBack<ReservationModel> {
            override fun onSuccessful(value: ReservationModel) {
                view.showAlert("ihul", true)
            }

            override fun onUnsuccessful(error: String) {
                view.showAlert(error, true)
            }

        })
    }
}