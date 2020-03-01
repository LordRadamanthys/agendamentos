package com.example.mulheresag.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.mulheresag.R

class DialogExamples {
    companion object {
        fun showDialogConfirm(message: String, status: Boolean, context: Context) {
            var dialog: Dialog = createDialog(
                context,
                if (status) R.layout.activity_dialog_confirm else R.layout.activity_dialog_error
            )
            var text: TextView =
                dialog.findViewById(if (status) R.id.textView_titulo_dialogConfirm else R.id.textView_titulo_dialogError)
            text.setText(message)
            var buttonConfirm: Button =
                dialog.findViewById(if (status) R.id.button_dialog_confirm else R.id.button_dialog_error)
            buttonConfirm.setOnClickListener {
                dialog.dismiss()
            }
        }

        fun showDialogChooser(activity:Activity, message: String, listener:DialogListener) {
            var dialog: Dialog = createDialog(
                activity,
                R.layout.activity_dialog_choose
            )
//            var text: TextView =
//                dialog.findViewById(if (status) R.id.textView_titulo_dialogConfirm else R.id.textView_titulo_dialogError)
//            text.setText(message)
            var buttonYes: Button = dialog.findViewById(R.id.button_dialog_sim_choose )
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
            var dialog:Dialog = Dialog(constex, R.style.CustomAlertDialog)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(layout)
            dialog.setCancelable(false)
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            dialog.show()
            return dialog
        }
         interface DialogListener{
            fun setChooser(chooser:Boolean)
        }
    }
}