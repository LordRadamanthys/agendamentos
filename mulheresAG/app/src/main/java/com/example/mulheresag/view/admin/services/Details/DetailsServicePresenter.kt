package com.example.mulheresag.view.admin.services.Details

import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.data.repository.ServicesRepository
import com.example.mulheresag.domain.services.ServiceDomain
import com.example.mulheresag.infra.BaseCallBack

class DetailsServicePresenter(var view: DetailsServiceContract.View) :
    DetailsServiceContract.Presenter {


    override fun createService(service: ServiceModel) {
        view.showProgresse(true)
        var domain = ServiceDomain()
        domain.repository = ServicesRepository()

        domain.createService(service,object :BaseCallBack<ServiceModel>{
            override fun onSuccessful(value: ServiceModel) {
                view.showProgresse(false)
                view.showService(value)
            }

            override fun onUnsuccessful(error: String) {
                view.showProgresse(false)
                view.showError(error)
            }

        })
    }

}