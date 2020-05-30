package com.example.mulheresag.view.admin.adminHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import kotlinx.android.synthetic.main.list_adapter_conversations.view.*

class AdapterAdminHome(list: ArrayList<UserModel>, context: Context) :
    RecyclerView.Adapter<AdapterAdminHome.MyViewHolder>() {
    private var listUsers = list
    private var context = context
    var onItemClick: ((UserModel) -> Unit)? = null


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nome = itemView.textViewNomeConversation
        val description = itemView.textViewDescriptionConversation
        val imageProfile = itemView.imageViewConversationUser

        init {
            itemView.setOnClickListener {
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
        setGlide(model.id, holder.imageProfile)
    }

    fun setGlide(id: Int, img: ImageView) {
        val url = "${App.ip}3333/uploads/$id"
        val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", App.userToken)) }

        Glide.with(this.context)
            .load(glideUrl)
            .fitCenter()
            .skipMemoryCache(true)
            .placeholder(R.drawable.useradd)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(img)


    }
}