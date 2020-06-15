package com.example.mulheresag.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.mulheresag.R
import com.example.mulheresag.infra.App
import com.example.mulheresag.view.login.LoginActivity

class DialogExamples {
    companion object {
        fun showDialogConfirm(message: String, status: Boolean, context: Context) {
            val dialog: Dialog = createDialog(
                context,
                if (status) R.layout.activity_dialog_confirm else R.layout.activity_dialog_error
            )
            val text: TextView =
                dialog.findViewById(if (status) R.id.textView_titulo_dialogConfirm else R.id.textView_titulo_dialogError)
            text.text = message
            val buttonConfirm: Button =
                dialog.findViewById(if (status) R.id.button_dialog_confirm else R.id.button_dialog_error)
            buttonConfirm.setOnClickListener {
                dialog.dismiss()
            }
        }


        fun showDialogConfirmClose(
            message: String,
            status: Boolean,
            context: Context,
            activity: Activity,
            closeActivity: Boolean
        ) {
            val dialog: Dialog = createDialog(
                context,
                if (status) R.layout.activity_dialog_confirm else R.layout.activity_dialog_error
            )
            val text: TextView =
                dialog.findViewById(if (status) R.id.textView_titulo_dialogConfirm else R.id.textView_titulo_dialogError)
            text.text = message
            val buttonConfirm: Button =
                dialog.findViewById(if (status) R.id.button_dialog_confirm else R.id.button_dialog_error)
            buttonConfirm.setOnClickListener {
                dialog.dismiss()
                if (closeActivity) {
                    val preferences = App.setPreferences(activity)
                    preferences.edit().putBoolean("admin", false).apply()
                    activity.finish()
                }

            }
        }


        fun showDialogExit(activity: Activity) {
            val dialog: Dialog = createDialog(
                activity,
                R.layout.activity_dialog_choose
            )
            val btnYes: Button = dialog.findViewById(R.id.button_dialog_sim_choose)
            val btnNo: Button = dialog.findViewById(R.id.button_dialog_nao_choose)

            btnYes.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                dialog.dismiss()
                val preferences = App.setPreferences(activity.applicationContext)
                preferences.edit().putBoolean("admin", false).apply()
                activity.startActivity(intent)
                activity.finish()
            }

            btnNo.setOnClickListener {
                dialog.dismiss()
            }
        }


        fun showDialogChooser(activity: Activity, message: String, listener: DialogListener) {
            var dialog: Dialog = createDialog(
                activity,
                R.layout.activity_dialog_choose
            )
//            var text: TextView =
//                dialog.findViewById(if (status) R.id.textView_titulo_dialogConfirm else R.id.textView_titulo_dialogError)
//            text.setText(message)
            var buttonYes: Button = dialog.findViewById(R.id.button_dialog_sim_choose)
            var buttonNo: Button = dialog.findViewById(R.id.button_dialog_nao_choose)
            buttonYes.setOnClickListener {
                listener.setChooser(true)
                dialog.dismiss()
            }

            buttonNo.setOnClickListener {
                listener.setChooser(false)
                dialog.dismiss()
            }
        }


        fun createDialog(constex: Context, layout: Int): Dialog {
            val dialog = Dialog(constex, R.style.CustomAlertDialog)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(layout)
            dialog.setCancelable(false)
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            dialog.show()
            return dialog
        }

        interface DialogListener {
            fun setChooser(chooser: Boolean)
        }
    }
}