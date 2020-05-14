package com.example.mulheresag.view.homePage

import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.repository.ReservationRepository
import com.example.mulheresag.domain.reservation.ReservationDomain
import com.example.mulheresag.infra.BaseCallBack

class HomePresenter(view: HomeContract.View) : HomeContract.Presenter {
    var view: HomeContract.View = view


    override fun getListReservations() {
        view.showProgressBar(true)
        var domain = ReservationDomain()
        domain.repository = ReservationRepository()

        domain.getListReservations(object : BaseCallBack<ArrayList<ReservationModel>> {
            override fun onSuccessful(value: ArrayList<ReservationModel>) {
                view.showProgressBar(false)
                value.reverse()
                view.setList(value)
            }

            override fun onUnsuccessful(error: String) {
                view.showProgressBar(false)
                view.showError(error)
            }
        })

    }
}