package com.example.mulheresag.view.admin.adminHome

import com.example.mulheresag.data.remote.model.UserModel

class AdminHomeContract {
    interface View{
        fun listUsers(list: ArrayList<UserModel>)
        fun showError(text:String)
        fun showProgressBar(key: Boolean)
    }

    interface Presenter{
        fun getAllUsers()
    }
}