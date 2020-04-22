package com.example.mulheresag.view.agendamento

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mulheresag.ChatActivity
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.CreateReservationModel
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel
import kotlinx.android.synthetic.main.fragment_agendamentos.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AgendamentosFragment : Fragment(), AgendamentosContract.View {
    lateinit var inflate: View
    private val cal = Calendar.getInstance()
    lateinit var presenter:AgendamentosContract.Presenter
    @SuppressLint("NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        presenter = AgendamentosPresenter(this)
        inflate = inflater.inflate(R.layout.fragment_agendamentos, container, false)
        timerPicker()
        datePicker()

        var reservas:ReservationModel = ReservationModel()
        var service= ServiceModel()
        var service2= ServiceModel()
        service.id=1
        service2.id=2

        var lista : ArrayList<ServiceModel> = ArrayList()
        lista.add(service)
        lista.add(service2)
        reservas.date="22/03/2020"
        reservas.hour= "22"
        reservas.description="teste pelo app"
        reservas.fullPrice=5.5
        reservas.services=lista
        var buttonReservar = inflate.button_reservar

        var model = CreateReservationModel()
        model.reservationModel=reservas

        buttonReservar.setOnClickListener {
//            val intent = Intent(activity, ChatActivity::class.java)
//            startActivity(intent)
            presenter.createReservation(reservas)


        }


        return inflate
    }

    private fun datePicker() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat)
                inflate.editText_dataReserva.setText(sdf.format(cal.time))

            }
        inflate.editText_dataReserva.setOnClickListener {
            DatePickerDialog(
                inflate.context, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun timerPicker() {
        inflate.editText_horasReserva.setOnClickListener {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                inflate.editText_horasReserva.setText(
                    String.format(
                        SimpleDateFormat("HH:mm").format(
                            cal.time
                        )
                    )
                )
            }
            TimePickerDialog(
                inflate.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()

        }
    }

    override fun showAlert(text: String, key: Boolean) {

    }

}
