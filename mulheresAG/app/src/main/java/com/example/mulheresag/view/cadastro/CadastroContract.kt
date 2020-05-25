package com.example.mulheresag.view.cadastro

import android.content.Intent
import com.example.mulheresag.data.remote.model.UserModel
import okhttp3.MultipartBody

class CadastroContract {

    interface View{
        fun showProgressBar(key:Boolean)
        fun setImage(token:String)
        fun showAlert(key:Boolean, text:String)
    }

    interface Presenter{
        fun createUser(user:UserModel)
        fun uploadImage(file: MultipartBody.Part, token: String)
    }

}