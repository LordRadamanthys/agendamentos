package com.example.mulheresag.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.mulheresag.MyFirebaseMessagingService
import com.example.mulheresag.R
import com.example.mulheresag.view.DefaultActivity
import com.example.mulheresag.view.cadastro.CadastroActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        button_entrar.setOnClickListener(View.OnClickListener {
            val home = Intent(this,DefaultActivity::class.java)
            startActivity(home)

        })

        textView_cadastro.setOnClickListener {
            val cadastro = Intent(this,CadastroActivity::class.java)
            startActivity(cadastro)
        }


        genareteTokenFirebaseDevice()

        MyFirebaseMessagingService()
    }

    private fun genareteTokenFirebaseDevice() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("teste", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast

                    Log.e("teste", token)
                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
    }
}
