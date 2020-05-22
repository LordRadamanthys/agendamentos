package com.example.mulheresag.view.admin.services.Details

import com.example.mulheresag.data.remote.model.ServiceModel

class DetailsServiceContract {
    interface View{
        fun showService(service: ServiceModel)
        fun showProgresse(key:Boolean)
        fun showAlert(text:String, key:Boolean)
    }

    interface Presenter{
        fun createService(service:ServiceModel)
        fun updateService(service:ServiceModel)
        fun getService(id:Int)
    }
}