package com.example.mulheresag.view.login

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.data.repository.AuthRepository
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.BaseCallBack
import java.lang.Exception

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter {
    var view: LoginContract.View

    init {
        this.view = view
    }

    override fun login(email: String, password: String) {
        var user = UserDomain(email, password)
        user.repository = AuthRepository()

        try {
            user.login(object : BaseCallBack<LoginModel> {
                override fun onSuccessful(value: LoginModel) {
                    view.navigateToHome(value)
                }

                override fun onUnsuccessful(error: String) {
                    view.showError(error)
                }

            })
        } catch (e: Exception) {
            e.message?.let { view.showError(it) }
        }

    }
}