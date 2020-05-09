package com.example.mulheresag.view.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.DefaultActivity
import com.example.mulheresag.view.cadastro.CadastroActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var presenter: LoginContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        presenter = LoginPresenter(this)



        loadActionsButton()

        genareteTokenFirebaseDevice()

        // MyFirebaseMessagingService()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel("com.example.mulheresag.view.login", name, importance).apply {
                    description = descriptionText
                }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun loadActionsButton() {
        button_entrar.setOnClickListener(View.OnClickListener {
            presenter.login(editText_email.text.toString(), editText_senha.text.toString())
            val home = Intent(this, DefaultActivity::class.java)
            //startActivity(home)


        })

        textView_cadastro.setOnClickListener {
            val cadastro = Intent(this, CadastroActivity::class.java)
            startActivity(cadastro)
        }
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
                App.tokenFirebase = token.toString()

                // Log and toast

                Log.e("teste", token)
                Toast.makeText(baseContext, App.tokenFirebase, Toast.LENGTH_SHORT).show()
            })
    }


    override fun navigateToHome(user: UserModel) {
        val home = Intent(this, DefaultActivity::class.java)
        startActivity(home)
    }

    override fun showError(error: String) {
        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    }
}
