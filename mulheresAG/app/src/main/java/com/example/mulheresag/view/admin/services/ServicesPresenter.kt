package com.example.mulheresag.view.admin.services

import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.data.repository.ServicesRepository
import com.example.mulheresag.domain.services.ServiceDomain
import com.example.mulheresag.infra.BaseCallBack

class ServicesPresenter(var view: ServicesContract.View) : ServicesContract.Presenter {


    override fun getListServices() {
        view.showProgresse(true)
        var domain = ServiceDomain()
        domain.repository = ServicesRepository()
        domain.getAllService(object : BaseCallBack<ArrayList<ServiceModel>> {
            override fun onSuccessful(value: ArrayList<ServiceModel>) {
                view.showProgresse(false)
                view.showList(value)
            }

            override fun onUnsuccessful(error: String) {
                view.showProgresse(false)
                view.showError(error)
            }

        })
    }
}