package com.example.mulheresag

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.model.ChatDataModel
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_chat.*
import java.net.URISyntaxException


class ChatActivity : AppCompatActivity() {
    lateinit var recyclerViewChatMessage: RecyclerView
    private var socket: Socket? = null
    private var Nickname: String? = null
    var list = mutableListOf<ChatDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_chat)
        loadList()

        Nickname = "Mateus"

        try {
            var socket1 = IO.socket("http://192.168.15.14:3000")
            socket1.connect()
            socket1.emit("join", Nickname)
            Log.e("teste","FOIII")

        } catch (e: URISyntaxException) {
            e.printStackTrace();

        }

        recyclerViewChatMessage = reyclerview_message_list
        recyclerViewChatMessage.adapter = AdapterChatMessage(list)
    }

    private fun loadList() {
        list.add(ChatDataModel("mateus", "mateus teste", 1))
        list.add(ChatDataModel("marcos", "marcos teste", 2))
        list.add(ChatDataModel("mateus", "mateus teste", 1))
        list.add(ChatDataModel("marcos", "marcos teste", 2))
        list.add(ChatDataModel("mateus", "message teste", 1))
        list.add(ChatDataModel("mateus", "message teste", 2))
        list.add(ChatDataModel("mateus", "message teste", 1))
        list.add(ChatDataModel("mateus", "message teste", 2))
        list.add(ChatDataModel("mateus", "message teste", 1))
        list.add(ChatDataModel("mateus", "message teste", 2))
        list.add(ChatDataModel("mateus", "message teste", 1))
        list.add(ChatDataModel("mateus", "message teste", 2))
        list.add(ChatDataModel("mateus", "message teste", 1))
        list.add(ChatDataModel("mateus", "message teste", 1))
    }
}
