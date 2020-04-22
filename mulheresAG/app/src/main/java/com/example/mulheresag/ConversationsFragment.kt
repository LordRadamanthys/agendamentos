package com.example.mulheresag

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.data.remote.model.UserModel
import kotlinx.android.synthetic.main.fragment_lis_conversations.view.*


/**
 * A simple [Fragment] subclass.
 */
class ConversationsFragment : Fragment() {
    lateinit var inflate: View
    lateinit var recyclerViewConversations: RecyclerView
    lateinit var listReservation: MutableList<UserModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(R.layout.fragment_lis_conversations, container, false)
listReservation = arrayListOf()
        loadLits()

        recyclerViewConversations = inflate.recycleConversation
        recyclerViewConversations.adapter = adapterConversations(listReservation)



        recyclerViewConversations.addOnItemTouchListener()
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
