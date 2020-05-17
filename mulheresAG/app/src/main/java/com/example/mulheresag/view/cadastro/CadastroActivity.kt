package com.example.mulheresag.view.cadastro

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.DialogExamples
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), CadastroContract.View {
    lateinit var presenter: CadastroContract.Presenter
    lateinit var progressBar:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        progressBar = ProgressBarCadastro

        presenter = CadastroPresenter(this)


        if (App.isAdmin) {
            switch_admin.visibility = View.VISIBLE
        }
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
        progressBar.visibility = if(key) View.VISIBLE else View.INVISIBLE
    }

    override fun showAlert(key: Boolean, text: String) {
        DialogExamples.showDialogConfirm(text, key, this)
    }
}
