package com.example.mulheresag.view.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.mulheresag.R
import com.example.mulheresag.view.DialogExamples
import com.example.mulheresag.view.DialogExamples.Companion.DialogListener
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_cadastro)

        var listenerDialog = object : DialogListener {
            override fun setChooser(chooser: Boolean) {
                if (chooser) Toast.makeText(baseContext, "foi", Toast.LENGTH_LONG).show()
            }
        }
        button_cadastrar.setOnClickListener {
            DialogExamples.showDialogChooser(this, "teste", listenerDialog)
        }
    }
}
