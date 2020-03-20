package com.example.mulheresag.view.cadastro

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.data.repository.UserRepository
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.BaseCallBack

class CadastroPresenter(view: CadastroContract.View) : CadastroContract.Presenter {

    var view: CadastroContract.View

    init {
        this.view = view
    }



    override fun createUser(user: UserModel) {
        var domain = UserDomain(user.email, user.password)
        domain.repository = UserRepository()
        domain.repository.createUser(user, object : BaseCallBack<UserModel> {
            override fun onSuccessful(value: UserModel) {
                view.showAlert(true,"foi")
            }

            override fun onUnsuccessful(error: String) {

            }

        })
    }
}