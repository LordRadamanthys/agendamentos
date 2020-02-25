package com.example.mulheresag.view.homePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.model.ReservationModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    var listReservation = mutableListOf<ReservationModel>()
    lateinit var recyclerHome: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home)


        loadLits()


        recyclerHome = recycleHome
        recyclerHome.adapter = AdapterHome(listReservation, this)


    }

    private fun loadLits() {
        this.listReservation.add(
            ReservationModel(
                "12:12",
                "12/12/2020",
                "teste teste et ets et ste ",
                "marcado"
            )
        )
        this.listReservation.add(
            ReservationModel(
                "12:12",
                "12/12/2020",
                "teste teste et ets et ste ",
                "marcado"
            )
        )
        this.listReservation.add(
            ReservationModel(
                "12:12",
                "12/12/2020",
                "teste teste et ets et ste ",
                "marcado"
            )
        )
        this.listReservation.add(
            ReservationModel(
                "12:12",
                "12/12/2020",
                "teste teste et ets et ste ",
                "marcado"
            )
        )
        this.listReservation.add(
            ReservationModel(
                "12:12",
                "12/12/2020",
                "teste teste et ets et ste ",
                "marcado"
            )
        )
    }
}
