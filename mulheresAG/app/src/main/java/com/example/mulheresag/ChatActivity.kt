package com.example.mulheresag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.model.ChatDataModel
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    lateinit var recyclerViewChatMessage: RecyclerView
    var list = mutableListOf<ChatDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_chat)
        loadList()

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
