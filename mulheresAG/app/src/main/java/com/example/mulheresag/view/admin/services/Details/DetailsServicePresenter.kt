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
        try {
            domain.createService(service, object : BaseCallBack<ServiceModel> {
                override fun onSuccessful(value: ServiceModel) {
                    view.showProgresse(false)
                    view.showAlert("Inserido", true)
                }

                override fun onUnsuccessful(error: String) {
                    view.showProgresse(false)
                    view.showAlert(error, false)
                }

            })
        } catch (e: Exception) {
            view.showProgresse(false)
            view.showAlert(e.message.toString(), false)
        }
    }

    override fun updateService(service: ServiceModel) {
        view.showProgresse(true)
        var domain = ServiceDomain()
        domain.repository = ServicesRepository()
        try {

            domain.updateService(service, object : BaseCallBack<String> {
                override fun onSuccessful(value: String) {
                    view.showProgresse(false)
                    view.showAlert("Atualizado", true)
                }

                override fun onUnsuccessful(error: String) {
                    view.showProgresse(false)
                    view.showAlert(error, false)
                }

            })
        } catch (e: Exception) {
            view.showProgresse(false)
            view.showAlert(e.message.toString(), false)
        }
    }

    override fun getService(id: Int) {
        view.showProgresse(true)
        var domain = ServiceDomain()
        domain.repository = ServicesRepository()

        domain.getService(id, object : BaseCallBack<ServiceModel> {
            override fun onSuccessful(value: ServiceModel) {
                view.showProgresse(false)
                view.showService(value)
            }

            override fun onUnsuccessful(error: String) {
                view.showProgresse(false)
                view.showAlert(error, false)
            }

        })
    }

}