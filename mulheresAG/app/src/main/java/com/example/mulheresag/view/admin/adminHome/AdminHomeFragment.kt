package com.example.mulheresag.view.admin.adminHome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.DialogExamples
import com.example.mulheresag.view.admin.adminHome.reservations.DetailsReservationActivity
import com.example.mulheresag.view.admin.services.Details.DetailsServiceContract
import com.example.mulheresag.view.homePage.HomeFragment
import kotlinx.android.synthetic.main.fragment_admin_home.*
import kotlinx.android.synthetic.main.fragment_admin_home.view.*
import kotlinx.android.synthetic.main.fragment_admin_home.view.textView_tituloAdminHome

/**
 * A simple [Fragment] subclass.
 */
class AdminHomeFragment : Fragment(), AdminHomeContract.View {
    private lateinit var inflate: View
    private lateinit var recyclerViewAdminHome: RecyclerView
    private lateinit var progressBarAdminHome: ProgressBar
    private lateinit var textTitleScreen: TextView
    lateinit var adapter: AdapterAdminHome
    lateinit var presenter: AdminHomeContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(R.layout.fragment_admin_home, container, false)
        textTitleScreen = inflate.textView_tituloAdminHome
        initComponents()
        presenter.getAllUsers()

        return inflate
    }

    private fun initComponents() {

        textTitleScreen.text = "Olá ${App.userName}"
        recyclerViewAdminHome = inflate.recycleAdminHomeFragment
        progressBarAdminHome = inflate.progressBarAdminHome

        presenter =
            AdminHomePresenter(this)
    }

    override fun listUsers(list: ArrayList<UserModel>) {
        adapter =
            AdapterAdminHome(list)
        recyclerViewAdminHome.adapter = adapter
        clickItem()
    }

    private fun clickItem() {
        adapter.onItemClick = { user ->
            Toast.makeText(context, user.name, Toast.LENGTH_LONG).show()
            var intent = Intent(activity,DetailsReservationActivity::class.java)
            intent.putExtra("id",user.id)
            startActivity(intent)
        }
    }

    override fun showError(text: String) {
        context?.let { DialogExamples.showDialogConfirm(text,true, it) }
    }

    override fun showProgressBar(key: Boolean) {
        progressBarAdminHome.visibility = if (key) View.VISIBLE else View.INVISIBLE
        recyclerViewAdminHome.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }


}
