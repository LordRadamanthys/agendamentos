package com.example.mulheresag.view.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel
import kotlinx.android.synthetic.main.list_adapter_home.view.*
import java.text.NumberFormat
import java.util.*


class AdapterHome(
    listReservation: MutableList<ReservationModel>
) : RecyclerView.Adapter<AdapterHome.MyViewHolder>() {
    private var listReservation = listReservation


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data = itemView.textView_data_adapterHome
        val horas = itemView.textView_horas_adapterHome
        val descricao = itemView.textView_descricao_adapterHome
        val status = itemView.textView_status_adapterHome
        val preco = itemView.textView_preco_adapterHome
        val titulo = itemView.textView_titulo_adapterHome
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemList = LayoutInflater.from(parent.context).inflate(R.layout.list_adapter_home,parent,false)
        return MyViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return  listReservation.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model = listReservation.get(position)

        holder.data.text = model.date
        holder.horas.text = model.hour+" horas"
        holder.status.text = model.status
        holder.descricao.text = model.description
        holder.preco.text = formatCurrency(model.fullPrice)
        holder.titulo.text = formatTitle(model.services)

    }

    private fun formatTitle(services: ArrayList<ServiceModel>?): CharSequence? {
        var text =""
        if (services != null) {
            services.forEach { t: ServiceModel? ->
                if (t != null) {
                  text+= t.title+"/ "
                }
            }
        }
        return text
    }

    private fun formatCurrency(value: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        //format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("BRL"))
        return format.format(value)
    }

}