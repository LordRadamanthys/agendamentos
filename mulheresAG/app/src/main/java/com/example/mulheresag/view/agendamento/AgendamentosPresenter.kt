package com.example.mulheresag.view.agendamento

import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.data.repository.ReservationRepository
import com.example.mulheresag.domain.reservation.ReservationDomain
import com.example.mulheresag.infra.BaseCallBack

class AgendamentosPresenter(view: AgendamentosContract.View) : AgendamentosContract.Presenter {
    var view: AgendamentosContract.View = view

    override fun createReservation(model: ReservationModel) {
        view.showProgressBar(true)
        var domain = ReservationDomain()
        domain.repository = ReservationRepository()
        try {
            domain.createReservation(model, object : BaseCallBack<ReservationModel> {
                override fun onSuccessful(value: ReservationModel) {
                    view.showProgressBar(false)
                    view.showAlert("Reserva efetuada :)", true)
                }

                override fun onUnsuccessful(error: String) {
                    view.showProgressBar(false)
                    view.showAlert(error, true)
                }

            })
        }catch (e:Exception){
            view.showProgressBar(false)
            view.showAlert(e.message.toString(), true)
        }
    }

    override fun getAllServoces() {
        view.showProgressBar(true)
        var domain = ReservationDomain()
        domain.repository = ReservationRepository()

        domain.getAllServices(object : BaseCallBack<ArrayList<ServiceModel>> {
            override fun onSuccessful(value: ArrayList<ServiceModel>) {
                view.showProgressBar(false)
                view.loadServicesList(value)
            }

            override fun onUnsuccessful(error: String) {
                view.showProgressBar(false)
                view.showAlert(error, true)
            }

        })
    }
}