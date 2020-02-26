package com.example.mulheresag.view.agendamento

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mulheresag.R
import kotlinx.android.synthetic.main.fragment_agendamentos.view.*
import java.text.SimpleDateFormat
import java.util.*

class AgendamentosFragment : Fragment() {
    lateinit var inflate: View
    private val cal = Calendar.getInstance()
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inflate = inflater.inflate(R.layout.fragment_agendamentos, container, false)

        timerPicker()
        datePicker()


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

}
