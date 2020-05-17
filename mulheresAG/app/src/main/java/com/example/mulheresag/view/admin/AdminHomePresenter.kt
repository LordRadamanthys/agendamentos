package com.example.mulheresag.view.admin

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.data.repository.UserRepository
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.BaseCallBack

class AdminHomePresenter(var view: AdminHomeContract.View):AdminHomeContract.Presenter {


    override fun getAllUsers() {
        view.showProgressBar(true)
        var domain = UserDomain("","")
        domain.repository = UserRepository()

        domain.getAllusers(object :BaseCallBack<ArrayList<UserModel>>{
            override fun onSuccessful(value: ArrayList<UserModel>) {
                view.showProgressBar(false)
                view.listUsers(value)
            }

            override fun onUnsuccessful(error: String) {
                view.showProgressBar(false)
                view.showError(error)
            }

        })
    }
}