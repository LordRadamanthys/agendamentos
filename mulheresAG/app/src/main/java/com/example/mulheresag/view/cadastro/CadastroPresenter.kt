package com.example.mulheresag.view.cadastro

import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.data.repository.UserRepository
import com.example.mulheresag.domain.user.UserDomain
import com.example.mulheresag.infra.BaseCallBack
import okhttp3.MultipartBody

class CadastroPresenter(view: CadastroContract.View) : CadastroContract.Presenter {

    var view: CadastroContract.View

    init {
        this.view = view
    }


    override fun createUser(user: UserModel) {
        view.showProgressBar(true)
        var domain = UserDomain(user.email, user.password)
        domain.repository = UserRepository()
        domain.repository.createUser(user, object : BaseCallBack<UserModel> {
            override fun onSuccessful(value: UserModel) {
                view.setImage("Bearer "+value.token)
                //view.showProgressBar(false)
               // view.showAlert(true, "Cadastro efetudo")
            }

            override fun onUnsuccessful(error: String) {
                view.showProgressBar(false)
                view.showAlert(true, error)
            }

        })
    }

    override fun uploadImage(file: MultipartBody.Part, token: String) {
       //view.showProgressBar(true)
        var domain=UserDomain("","")
        domain.repository = UserRepository()
        domain.uploadPhoto(file,token,object :BaseCallBack<String>{
            override fun onSuccessful(value: String) {
                view.showProgressBar(false)
                view.showAlert(true, "Cadastro efetudo")
            }

            override fun onUnsuccessful(error: String) {
                view.showProgressBar(false)
                view.showAlert(true, error)
            }

        })
    }



}