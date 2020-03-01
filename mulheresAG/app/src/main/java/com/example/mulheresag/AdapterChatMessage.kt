package com.example.mulheresag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.model.ChatDataModel
import kotlinx.android.synthetic.main.item_message_received.view.*
import kotlinx.android.synthetic.main.item_message_send.view.*

class AdapterChatMessage(
    list: MutableList<ChatDataModel>) :
    RecyclerView.Adapter<AdapterChatMessage.MyViewHolder>() {
    private var list = list


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var message = if(itemView.textView_message_re!=null) itemView.textView_message_re else itemView.textView_message_send

    }


    override fun getItemViewType(position: Int): Int {
        var type = when (list.get(position).type) {
            1 -> 1
            2 -> 2
            else -> 1
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemList = when (viewType) {
            1 -> LayoutInflater.from(parent.context).inflate(
                R.layout.item_message_send,
                parent,
                false
            )
            2 -> LayoutInflater.from(parent.context).inflate(
                R.layout.item_message_received,
                parent,
                false
            )
            else -> LayoutInflater.from(parent.context).inflate(
                R.layout.item_message_send,
                parent,
                false
            )
        }
//        return viewHolder
//        var itemList = LayoutInflater.from(parent.context).inflate(R.layout.item_message_received,parent,false)

        return MyViewHolder(itemList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model = list.get(position)

      if(model.type==1){
          holder.message.text = model.message
      }else{
          holder.message.text = model.message
      }



    }

}