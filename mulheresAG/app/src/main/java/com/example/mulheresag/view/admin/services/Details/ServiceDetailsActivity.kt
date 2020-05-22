package com.example.mulheresag.view.admin.services.Details

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.view.admin.services.ServicesContract
import com.google.android.material.textfield.TextInputEditText

class ServiceDetailsActivity : AppCompatActivity(), DetailsServiceContract.View {

    private lateinit var title: TextInputEditText
    private lateinit var value: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var radio: RadioGroup
    private lateinit var presenter: DetailsServiceContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        presenter = DetailsServicePresenter(this)

    }

    override fun showService(service: ServiceModel) {
        TODO("Not yet implemented")
    }

    override fun createService(service: ServiceModel) {
        TODO("Not yet implemented")
    }

    override fun showProgresse(key: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showError(text: String) {
        TODO("Not yet implemented")
    }

}
