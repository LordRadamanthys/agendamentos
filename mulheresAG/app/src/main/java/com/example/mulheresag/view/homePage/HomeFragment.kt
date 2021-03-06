package com.example.mulheresag.view.homePage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.cadastro.CadastroActivity
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.fragment_admin_home.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.textView_tituloHome


class HomeFragment : Fragment(), HomeContract.View {

    lateinit var recyclerViewHome: RecyclerView
    lateinit var inflate: View
    lateinit var textTitleScreen: TextView
    lateinit var progressBarHome: ProgressBar
    private lateinit var imageUser: CircularImageView
    private lateinit var btnBack: ImageView
    lateinit var presenter: HomeContract.Presenter
    private var especifyUserId = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(R.layout.fragment_home, container, false)
        textTitleScreen = inflate.textView_tituloHome
        val textSubtitle = inflate.textView_mensagemHome
        try {
            especifyUserId = arguments?.get("id") as Int
        } catch (e: Exception) {
            especifyUserId = -1
        }
        initComponents()

        if (especifyUserId < 0) {
            presenter.getListReservations()
            btnBack.visibility = View.INVISIBLE
        } else {
            textSubtitle.text = "Agendamentos do contato"

            presenter.getListReservations(especifyUserId)

        }



        imageUser.setOnClickListener {
            var intent = Intent(activity, CadastroActivity::class.java)
            intent.putExtra("id", App.user.id)
            startActivity(intent)
        }


        return inflate
    }

    private fun initComponents() {
        imageUser = inflate.circularImageViewHome
        textTitleScreen.text = "Olá ${App.userName}"
        progressBarHome = inflate.progressBarHome
        recyclerViewHome = inflate.recycleHomeFragment
        btnBack = inflate.imageViewHomeBack
        setGlide()
        backPage()
        presenter = HomePresenter(this)
    }

    fun backPage() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        return
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

    private fun openFragment(fragment: Fragment?) {
        if (fragment != null) {
            fragmentManager?.beginTransaction()?.replace(android.R.id.content, fragment)?.commit()
        }
    }

    fun setGlide() {
        val url = "${App.ip}3333/uploads/${App.user.id}"
        val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", App.userToken)) }

        Glide.with(inflate.context)
            .load(glideUrl)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageUser)

    }
    override fun onStart() {
        super.onStart()
        setGlide()
    }


}
