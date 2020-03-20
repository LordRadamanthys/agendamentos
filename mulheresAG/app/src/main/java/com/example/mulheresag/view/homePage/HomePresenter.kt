package com.example.mulheresag.view.homePage

import android.view.View
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.repository.ReservationRepository
import com.example.mulheresag.domain.reservation.ReservationDomain
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository

class HomePresenter(view: HomeContract.View) : HomeContract.Presenter {
    var view: HomeContract.View

    init {
        this.view = view
    }


    override fun getListReservations() {
        var domain = ReservationDomain()
        domain.repository = ReservationRepository()

        domain.getListReservations(object : BaseCallBack<ArrayList<ReservationModel>> {
            override fun onSuccessful(value: ArrayList<ReservationModel>) {
                view.setList(value)
            }

            override fun onUnsuccessful(error: String) {

            }
        })

    }
}