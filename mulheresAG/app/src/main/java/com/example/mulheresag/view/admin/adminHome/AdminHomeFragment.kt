package com.example.mulheresag.view.admin.adminHome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.DialogExamples
import com.example.mulheresag.view.admin.adminHome.reservations.DetailsReservationActivity
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.fragment_admin_home.view.*

class AdminHomeFragment : Fragment(), AdminHomeContract.View {
    private lateinit var inflate: View
    private lateinit var recyclerViewAdminHome: RecyclerView
    private lateinit var progressBarAdminHome: ProgressBar
    private lateinit var textTitleScreen: TextView
    private lateinit var imageUser: CircularImageView
    lateinit var adapter: AdapterAdminHome
    lateinit var presenter: AdminHomeContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(R.layout.fragment_admin_home, container, false)


        initComponents()

        presenter.getAllUsers()

        return inflate
    }

    private fun initComponents() {
        textTitleScreen = inflate.textView_tituloAdminHome
        imageUser = inflate.circularImageViewAdminHome
        textTitleScreen.text = "Olá ${App.userName}"
        recyclerViewAdminHome = inflate.recycleAdminHomeFragment
        progressBarAdminHome = inflate.progressBarAdminHome
        setGlide()
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
            var intent = Intent(activity, DetailsReservationActivity::class.java)
            intent.putExtra("id", user.id)
            startActivity(intent)
        }
    }

    override fun showError(text: String) {
        context?.let { DialogExamples.showDialogConfirm(text, true, it) }
    }

    override fun showProgressBar(key: Boolean) {
        progressBarAdminHome.visibility = if (key) View.VISIBLE else View.INVISIBLE
        recyclerViewAdminHome.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }

    fun setGlide() {
        val url = "${App.ip}3333/uploads/${App.user.id}"
        val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", App.userToken)) }

        Glide.with(inflate.context)
            .load(glideUrl)
            .into(imageUser)

    }


}
