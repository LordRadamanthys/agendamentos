package com.example.mulheresag.view.login

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.BaseCallBack

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter {
     var view: LoginContract.View

    init {
        this.view = view
    }

    override fun login(email: String, password: String) {
        var user = UserDomain(email,password)

        user.login(object : BaseCallBack<UserModel> {
            override fun onSuccessful(value: UserModel) {
              view.navigateToHome(value)
            }

            override fun onUnsuccessful(error: String) {

            }

        })
    }
}