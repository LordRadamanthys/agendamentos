package com.example.mulheresag.view.admin.services.Details

import com.example.mulheresag.data.remote.model.ServiceModel

class DetailsServiceContract {
    interface View{
        fun showService(service: ServiceModel)
        fun createService(service: ServiceModel)
        fun showProgresse(key:Boolean)
        fun showError(text:String)
    }

    interface Presenter{
        fun createService(service:ServiceModel)
    }
}