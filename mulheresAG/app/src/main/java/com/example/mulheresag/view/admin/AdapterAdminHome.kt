package com.example.mulheresag.view.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import kotlinx.android.synthetic.main.list_adapter_conversations.view.*

class AdapterAdminHome(list: ArrayList<UserModel>): RecyclerView.Adapter<AdapterAdminHome.MyViewHolder>(){
    private var listUsers = list
    var onItemClick:((UserModel)->Unit)?=null


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nome = itemView.textViewNomeConversation
        val description = itemView.textViewDescriptionConversation
        init{
            itemView.setOnClickListener{
                onItemClick?.invoke(listUsers.get(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemList = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_adapter_conversations, parent, false)
        return MyViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model = listUsers[position]
        holder.nome.text = model.name
        holder.description.text = model.email
    }
}