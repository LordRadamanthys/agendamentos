package com.example.mulheresag.view.admin.services

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.ServiceModel
import kotlinx.android.synthetic.main.list_adapter_services.view.*

class AdapterServices(var listServices: ArrayList<ServiceModel>) :
    RecyclerView.Adapter<AdapterServices.MyViewHolder>() {
    var onItemClick: ((ServiceModel) -> Unit)? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titulo = itemView.textView_titulo_adapterService
        var valor = itemView.textView_preco_adapterService
        var description = itemView.textView_descricao_adapterService
        var status = itemView.textView_status_adapterService

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listServices.get(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemList = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_adapter_services, parent, false)
        return MyViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return listServices.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model = listServices[position]

        holder.titulo.text = model.title
        holder.valor.text = "${model.value}"
        holder.description.text = model.description
        holder.status.text = if (model.status) "Ativo" else "Desativado"
        if (model.status) holder.status.setTextColor(Color.GREEN) else holder.status.setTextColor(Color.RED)
    }
}