package com.example.mulheresag.view.login

import com.example.mulheresag.data.remote.model.LoginModel
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.domain.user.UserDomain

class LoginContract{
    interface View{
        fun navigateToHome(user: UserModel)
        fun showError(error: String)
    }

    interface Presenter{
        fun login(email:String, password:String)
    }
}