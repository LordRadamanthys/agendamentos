package com.example.mulheresag.view.admin.adminHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.view.DialogExamples
import kotlinx.android.synthetic.main.fragment_admin_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class AdminHomeFragment : Fragment(), AdminHomeContract.View {
    private lateinit var inflate: View
    private lateinit var recyclerViewAdminHome: RecyclerView
    private lateinit var progressBarAdminHome: ProgressBar
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
