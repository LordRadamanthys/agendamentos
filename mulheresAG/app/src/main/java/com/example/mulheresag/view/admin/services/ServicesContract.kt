package com.example.mulheresag.view.admin.services

import com.example.mulheresag.data.remote.model.ServiceModel

class ServicesContract {
    interface View{
        fun showList(list:ArrayList<ServiceModel>)
        fun showProgresse(key:Boolean)
        fun showError(text:String)
    }



    interface Presenter{
        fun getListServices()
    }
}