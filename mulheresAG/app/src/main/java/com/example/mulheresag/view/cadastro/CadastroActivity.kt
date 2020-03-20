package com.example.mulheresag.view.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.DialogExamples
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), CadastroContract.View {
    lateinit var presenter: CadastroContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        presenter = CadastroPresenter(this)
//        var listenerDialog = object : DialogListener {
//            override fun setChooser(chooser: Boolean) {
//                if (chooser) Toast.makeText(baseContext, "foi", Toast.LENGTH_LONG).show()
//            }
//        }

        button_cadastrar.setOnClickListener {
            var userModel = UserModel()
             userModel.name = editText_nomeCad.text.toString()
            userModel.email = editText_emailCad.text.toString()
            userModel.password = editText_senhaCad.text.toString()
            userModel.tokenDevice = App.tokenFirebase


           // DialogExamples.showDialogChooser(this, "teste", listenerDialog)
            presenter.createUser(userModel)
        }
    }

    override fun showProgressBar(key: Boolean) {

    }

    override fun showAlert(key: Boolean, text: String) {
        DialogExamples.showDialogConfirm(text,key,this)
    }
}
