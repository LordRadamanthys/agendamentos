package com.example.mulheresag.view.admin.services

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.view.DialogExamples
import com.example.mulheresag.view.admin.services.Details.DetailsServiceContract
import com.example.mulheresag.view.admin.services.Details.ServiceDetailsActivity
import kotlinx.android.synthetic.main.fragment_services.*
import kotlinx.android.synthetic.main.fragment_services.view.*
import kotlinx.android.synthetic.main.fragment_services.view.button_Add_ServiceFragment

/**
 * A simple [Fragment] subclass.
 */
class ServicesFragment : Fragment(), ServicesContract.View {
    private lateinit var inflate: View
    private lateinit var recyclerViewService: RecyclerView
    private lateinit var progress: View
    private lateinit var btnAdd: Button
    private lateinit var activityDetails: Intent
    private lateinit var adapter: AdapterServices
    private lateinit var presenter: ServicesContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(R.layout.fragment_services, container, false)

        initComponents()
        presenter.getListServices()

        btnAdd.setOnClickListener {
            activityDetails = Intent(activity, ServiceDetailsActivity::class.java)
            startActivity(activityDetails)
        }
        return inflate
    }

    private fun initComponents() {
        recyclerViewService = inflate.recycleServicesFragment
        btnAdd = inflate.button_Add_ServiceFragment
        progress = inflate.progressBarServices

        presenter = ServicesPresenter(this)
    }

    override fun showList(list: ArrayList<ServiceModel>) {
        adapter = AdapterServices(list)
        recyclerViewService.adapter = adapter
        clickItem()
    }

    fun clickItem() {
        adapter.onItemClick = { service ->
            Toast.makeText(context, service.title, Toast.LENGTH_LONG).show()
            activityDetails = Intent(activity, ServiceDetailsActivity::class.java)
            activityDetails.putExtra("id", service.id)
            startActivity(activityDetails)
        }
    }

    override fun showProgresse(key: Boolean) {
        progress.visibility = if (key) View.VISIBLE else View.INVISIBLE
        recyclerViewService.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }

    override fun showError(text: String) {
        context?.let { DialogExamples.showDialogConfirm(text, true, it) }
    }

    override fun onResume() {
        super.onResume()
        presenter.getListServices()
    }



}
