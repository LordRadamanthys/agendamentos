package com.example.mulheresag.view.admin.services.Details

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.view.DialogExamples
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_service_details.*

class ServiceDetailsActivity : AppCompatActivity(), DetailsServiceContract.View {

    private lateinit var title: EditText
    private lateinit var value: EditText
    private lateinit var description: EditText
    private lateinit var radio: RadioGroup
    private lateinit var progressBar: ProgressBar
    private var status = true
    private lateinit var btn: Button
    private lateinit var modelService: ServiceModel
    private lateinit var presenter: DetailsServiceContract.Presenter
    private var idExtra = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        initComponents()

        if (idExtra != -1) {
            btn.setText("Editar")
            presenter.getService(idExtra)
        }
        //verifyStatus()

        actionsButton()


    }

    private fun actionsButton() {
        radio.setOnCheckedChangeListener { group, checkedId ->
            status = checkedId == R.id.radio_Details_Service_ativado
        }



        btn.setOnClickListener {
            if (verifyFields()) {
                showAlert("Preencha todos os campos",!verifyFields())
                return@setOnClickListener
            }
            if (idExtra != -1) {
                presenter.updateService(setModel())
            } else {
                presenter.createService(setModel())
            }
        }
    }

    private fun initComponents() {
        progressBar = ProgressBarDetailsService
        title = title_DetailsService
        value = value_DetailsService
        description = description_DetailsService
        btn = button_Details_Service
        radio = radioGroup_Details_Service
        presenter = DetailsServicePresenter(this)
        idExtra = getIntent().getIntExtra("id", -1)
    }

    private fun verifyStatus() {
        if (status) {
            radio_Details_Service_desativado.isChecked = false
            radio_Details_Service_ativado.isChecked = true
        } else {
            radio_Details_Service_ativado.isChecked = false
            radio_Details_Service_desativado.isChecked = true
        }
    }

    private fun setModel(): ServiceModel {
        modelService = ServiceModel()
        modelService.id = idExtra
        modelService.title = title.text.toString()
        modelService.value = value.text.toString().toDouble()
        modelService.description = description.text.toString()
        modelService.status = status

        return modelService
    }

    override fun showService(service: ServiceModel) {
        title.setText(service.title)
        value.setText("${service.value}")
        description.setText(service.description)
        status = service.status
        verifyStatus()
    }


    private fun verifyFields(): Boolean {
        if (title.text.toString().isEmpty()) return true
        if (value.text.toString().isEmpty()) return true
        if (description.text.toString().isEmpty()) return true
        return false
    }

    override fun showProgresse(key: Boolean) {
        progressBar.visibility = if (key) View.VISIBLE else View.INVISIBLE
        btn.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }

    override fun showAlert(text: String, key:Boolean) {
        DialogExamples.showDialogConfirmClose(text, key, this,this,key)
        cleanFiels()

    }

    private fun cleanFiels() {
        title.text?.clear()
        value.text?.clear()
        description.text?.clear()
    }


}
