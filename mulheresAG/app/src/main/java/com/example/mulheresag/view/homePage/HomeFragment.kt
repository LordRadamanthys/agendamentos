package com.example.mulheresag.view.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ReservationModel
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    var listReservation = mutableListOf<ReservationModel>()
    lateinit var recyclerViewHome: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        var inflate = inflater.inflate(R.layout.fragment_home, container, false)


        loadLits()



        recyclerViewHome = inflate.recycleHomeFragment
        recyclerViewHome.adapter = AdapterHome(listReservation)
        // Inflate the layout for this fragment

        return inflate
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
