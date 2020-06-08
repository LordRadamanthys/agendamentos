package com.example.mulheresag.view.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
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
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var textRegister: TextView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        presenter = LoginPresenter(this)

        initComponents()

        loadActionsButton()

        genareteTokenFirebaseDevice()

        // MyFirebaseMessagingService()
    }

    private fun initComponents() {
        btnLogin = button_entrar
        textRegister = textView_cadastro
        textRegister.underline()
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
        btnLogin.setOnClickListener {
            presenter.login(editText_email.text.toString(), editText_senha.text.toString())
        }

        textRegister.setOnClickListener {
            val cadastro = Intent(this, CadastroActivity::class.java)
            startActivity(cadastro)
        }
    }

    private fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun genareteTokenFirebaseDevice() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // Log.w("teste", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                App.tokenFirebase = token.toString()

                // Log and toast

                Log.e("teste", token)
                //Toast.makeText(baseContext, App.tokenFirebase, Toast.LENGTH_SHORT).show()
            })
    }


    override fun navigateToHome(user: UserModel) {
        var preferences = App.setPreferences(this)
        preferences.edit().putInt("id", user.id).commit()
        preferences.edit().putString("name", user.name).commit()
        preferences.edit().putString("email", user.email).commit()
        preferences.edit().putString("token", user.token).commit()
        preferences.edit().putBoolean("admin", user.admin).commit()
        preferences.edit().putBoolean("saveLogin", false).commit()


        val home = Intent(this, DefaultActivity::class.java)
        startActivity(home)
        finish()
    }

    override fun showError(error: String) {
        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(key: Boolean) {
        progressBarLogin.visibility = if (key) View.VISIBLE else View.INVISIBLE
        button_entrar.visibility = if (!key) View.VISIBLE else View.INVISIBLE
    }
}
