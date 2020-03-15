package com.example.mulheresag

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mulheresag.model.ChatDataModel
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException


class ChatActivity : AppCompatActivity() {

    private val TAG = "PermissionDemo"
    lateinit var recyclerViewChatMessage: RecyclerView
    lateinit var socket: Socket
    private var Nickname: String? = null
    var list = mutableListOf<ChatDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setupPermissions()


        recyclerViewChatMessage = reyclerview_message_list

//        loadList()

        Nickname = "Vlad"

        try {
            socket = IO.socket("http://192.168.15.14:3000")
            socket.connect()
            socket.emit("join", Nickname)

        } catch (e: URISyntaxException) {
            e.printStackTrace();

        }

        socket.on("userjoinedthechat", Emitter.Listener { args ->
            runOnUiThread(Runnable {
                kotlin.run {
                    var data: String = args[0] as String
                    //Toast.makeText(applicationContext, data, Toast.LENGTH_LONG).show()
                }
            })
        })


        button_chatbox_send.setOnClickListener {
            if (!edittext_chatbox.text.toString().isEmpty()) {

                socket.emit("messagedetection", Nickname, edittext_chatbox.text.toString());
                edittext_chatbox.setText(" ");
            }
        }

        socket.on("message") { args ->
            runOnUiThread {

                val data = args[0] as JSONObject
                try { //extract data from fired event

                    val nickname = data.getString("senderNickname")
                    val message = data.getString("message")
                    var chatDataModel: ChatDataModel
                    if (Nickname.equals(nickname)) {
                        chatDataModel = ChatDataModel(nickname, message, 1)
                    } else {
                        chatDataModel = ChatDataModel(nickname, message, 2)
                    }
                    // make instance of message

                    list.add(chatDataModel)
                    // notify the adapter to update the recycler view
                    var adapterChatMessage = AdapterChatMessage(list)
                    // recyclerViewChatMessage.smoothScrollToPosition(list.size-1)
                    adapterChatMessage.notifyDataSetChanged()
                    recyclerViewChatMessage.adapter = adapterChatMessage


                    //set the adapter for the recycler view
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        socket.on("userdisconnect") { args ->
            runOnUiThread {
                val data = args[0] as String
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }
        }
        socket.disconnect();
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

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.RECORD_AUDIO
                )
            ) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Permission to access the microphone is required for this app to record audio.")
                    .setTitle("Permission required")

                builder.setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    Log.i(TAG, "Clicked")
                    makeRequest()
                }

                val dialog = builder.create()
                dialog.show()
            } else {
                makeRequest()
            }
        } else {
            Log.i(TAG, "Permission to granted")
        }

    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 8)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

            Log.i(TAG, "Permission has been denied by user")
        } else {
            Log.i(TAG, "Permission has been granted by user")
        }
    }
}
