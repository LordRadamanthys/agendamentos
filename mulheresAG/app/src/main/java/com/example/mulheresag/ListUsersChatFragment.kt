package com.example.mulheresag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.data.remote.model.UserModel
import kotlinx.android.synthetic.main.fragment_list_users_chat.view.*

/**
 * A simple [Fragment] subclass.
 */
class ListUsersChatFragment : Fragment() {
    lateinit var inflate: View
    lateinit var listReservation: MutableList<UserModel>
    lateinit var recycleListUsersChat: RecyclerView
    lateinit var adapter: adapterConversations
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_list_users_chat, container, false)
        listReservation = arrayListOf()
        loadLits()
        adapter = adapterConversations(listReservation)
        recycleListUsersChat = inflate.recycleListUsersChat
        recycleListUsersChat.adapter = adapter

        adapter.onItemClick = { user ->
            var intent = Intent(context,ChatActivity().javaClass)
            startActivity(intent)
        }

        return inflate
    }

    private fun loadLits() {
        var user = UserModel()
        user.name = "mateus"
        user.email = "mateuslima565@gmail.com"
        user.tokenDevice = "mateuslima565@gmail.com"
        user.token = "mateuslima565@gmail.com"
        user.password = "mateuslima565@gmail.com"
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)
        this.listReservation.add(user)


    }
}
