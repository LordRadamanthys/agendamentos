package com.example.mulheresag.view.login

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.data.repository.UserRepository
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter {
    var view: LoginContract.View = view

    override fun login(email: String, password: String) {
        view.showProgressBar(true)
        val user = UserDomain(email, password)
        user.repository = UserRepository()

        try {
            user.login(object : BaseCallBack<UserModel> {
                override fun onSuccessful(value: UserModel) {
                    App.userToken = "Bearer ${value.token}"
                    view.showProgressBar(false)
                    view.navigateToHome(value)
                }

                override fun onUnsuccessful(error: String) {
                    view.showProgressBar(false)
                    view.showError(error)
                }

            })
        } catch (e: Exception) {
            view.showProgressBar(false)
            e.message?.let { view.showError(it) }
        }

    }

}