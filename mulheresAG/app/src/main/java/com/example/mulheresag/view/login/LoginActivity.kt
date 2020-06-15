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
import android.widget.*
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
import kotlinx.android.synthetic.main.activity_main.view.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var textRegister: TextView
    private lateinit var btnLogin: Button
    private lateinit var swicthSaveLogin: Switch
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

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
        editTextEmail =  editText_email
        editTextPassword = editText_senha
        swicthSaveLogin = switchLembrarSenha
        textRegister = textView_cadastro
        textRegister.underline()
        verifyPreferencesLogin()
    }

    private fun verifyPreferencesLogin(){
        val preferences = App.setPreferences(this)
        val hasPreferences = preferences.getBoolean("saveLogin",false)
        if(hasPreferences){
            editTextEmail.setText(preferences.getString("email",""))
            editTextPassword.setText(preferences.getString("password",""))
            swicthSaveLogin.isChecked = hasPreferences
        }
    }

    private fun createNotificationChannel() {
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
            presenter.login(editTextEmail.text.toString(), editTextPassword.text.toString())
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
        val preferences = App.setPreferences(this)
        preferences.edit().putInt("id", user.id).apply()
        preferences.edit().putString("name", user.name).apply()
        preferences.edit().putString("email", user.email).apply()
        preferences.edit().putString("password", editTextPassword.text.toString()).apply()
        preferences.edit().putString("token", "Bearer "+user.token).apply()
        preferences.edit().putBoolean("admin", user.admin).apply()
        preferences.edit().putBoolean("saveLogin", swicthSaveLogin.isChecked).apply()


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
