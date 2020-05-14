package com.example.mulheresag.view.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ReservationModel
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeContract.View {
    lateinit var recyclerViewHome: RecyclerView
    lateinit var inflate: View
    lateinit var progressBarHome: ProgressBar
    lateinit var presenter: HomeContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        inflate = inflater.inflate(R.layout.fragment_home, container, false)
        initComponents()

        presenter.getListReservations()


        return inflate
    }

    private fun initComponents() {
        progressBarHome = inflate.progressBarHome
        recyclerViewHome = inflate.recycleHomeFragment
        presenter = HomePresenter(this)
    }


    override fun setList(list: ArrayList<ReservationModel>) {

        recyclerViewHome.adapter = AdapterHome(list)
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(key: Boolean) {
        progressBarHome.visibility = if (key) View.VISIBLE else View.INVISIBLE
        recyclerViewHome.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }

}
