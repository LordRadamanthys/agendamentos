package com.example.mulheresag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.data.remote.model.UserModel
import kotlinx.android.synthetic.main.fragment_lis_conversations.view.*
import kotlinx.android.synthetic.main.list_adapter_conversations.view.*
import kotlinx.android.synthetic.main.list_adapter_home.view.*

class adapterConversations(listReservation: MutableList<UserModel>):
    RecyclerView.Adapter<adapterConversations.MyViewHolder>() {
    private var listReservation = listReservation

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nome = itemView.textViewNomeConversation
        val description = itemView.textViewDescriptionConversation

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemList = LayoutInflater.from(parent.context).inflate(R.layout.list_adapter_conversations,parent,false)
        return MyViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return  listReservation.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model = listReservation.get(position)

        holder.nome.text = model.name
        holder.description.text = model.email

    }

}