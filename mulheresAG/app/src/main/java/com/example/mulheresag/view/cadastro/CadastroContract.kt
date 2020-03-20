package com.example.mulheresag.view.cadastro

import com.example.mulheresag.data.remote.model.UserModel

class CadastroContract {

    interface View{
        fun showProgressBar(key:Boolean)

        fun showAlert(key:Boolean, text:String)
    }

    interface Presenter{
        fun createUser(user:UserModel)
    }

}