package com.example.mulheresag.view.agendamento

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout
import androidx.fragment.app.Fragment
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.view.DialogExamples
import kotlinx.android.synthetic.main.fragment_agendamentos.*
import kotlinx.android.synthetic.main.fragment_agendamentos.view.*
import java.text.SimpleDateFormat
import java.util.*

class AgendamentosFragment : Fragment(), AgendamentosContract.View {
    private lateinit var inflate: View
    private lateinit var listServices: ArrayList<ServiceModel>
    private lateinit var selectedListServices: ArrayList<ServiceModel>
    private lateinit var editTextServices: EditText
    private lateinit var listNameServices: ArrayList<String>
    private val cal = Calendar.getInstance()
    private lateinit var presenter: AgendamentosContract.Presenter
    private lateinit var selectedList: ArrayList<Boolean>
    private var servicesSelected = ""
    private lateinit var buttonReservar: Button
    private var totalValueServices = 0.0
    private lateinit var progressBar :View

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        inflate = inflater.inflate(R.layout.fragment_agendamentos, container, false)

        initAtributes()
        timerPicker()
        datePicker()
        presenter.getAllServoces()

        editTextServices.setOnClickListener {

            alertChooseServices()
        }




        buttonReservar.setOnClickListener {
            val reservas: ReservationModel = makeModel()
            presenter.createReservation(reservas)

        }

        return inflate
    }

    private fun alertChooseServices() {
        val builder = AlertDialog.Builder(context,R.style.StyleClockAndDate)
        builder.setTitle("Selecione os serviços")


        builder.setMultiChoiceItems(
            this.listNameServices.toList().toTypedArray(),
            selectedList.toList().toBooleanArray()
        ) { dialog, which, isChecked ->
            selectedList.set(which, isChecked)


            if (isChecked) {
                selectedListServices.add(listServices.get(which))
            } else {
                selectedListServices.remove(listServices.get(which))
            }
            servicesSelected = ""
            if (selectedListServices.size < 1) {
                totalValueServices = 0.0
            } else {
                totalValueServices = 0.0
                selectedListServices.forEachIndexed { index, b ->
                    servicesSelected += "${b.title}  -  ${b.value}\n"
                    totalValueServices += b.value
                }
            }
        }

        builder.setPositiveButton("adicionar") { dialog, which ->
            editTextServices.setText("${servicesSelected} \n\n\t ${totalValueServices}")

            dialog.dismiss()
        }

        builder.show()
    }

    private fun initAtributes() {
        presenter = AgendamentosPresenter(this)

        progressBar = inflate.progressBarAgendamentosConstraint
        editTextServices = inflate.editTextServicos
        listNameServices = arrayListOf()
        selectedList = arrayListOf()
        selectedListServices = arrayListOf()
        buttonReservar = inflate.button_reservar

    }

    private fun makeModel(): ReservationModel {
        var reservas = ReservationModel()

        reservas.date = editText_dataReserva.text.toString().trim()
        reservas.hour = editText_horasReserva.text.toString().trim()
        reservas.description = editTextDescricao.text.toString().trim()
        reservas.fullPrice = totalValueServices
        reservas.services = selectedListServices
        return reservas
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
                inflate.context,R.style.StyleClockAndDate, dateSetListener,
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
                R.style.StyleClockAndDate,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()

        }
    }

    private fun clearAll() {
        editText_dataReserva.text.clear()
        editText_horasReserva.text.clear()
        editTextDescricao.text.clear()
        totalValueServices = 0.0
//        selectedListServices
    }

    override fun showAlert(text: String, key: Boolean) {
        context?.let { DialogExamples.showDialogConfirm(text, key, it) }
        clearAll()
    }

    override fun loadServicesList(listServices: ArrayList<ServiceModel>) {
        this.listServices = listServices
        this.listServices.forEachIndexed { index, serviceModel ->
            listNameServices.add(serviceModel.title)
            selectedList.add(false)
        }
    }

    override fun showProgressBar(key: Boolean) {
        progressBar.visibility = if (key) View.VISIBLE else View.INVISIBLE
        buttonReservar.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }
}



