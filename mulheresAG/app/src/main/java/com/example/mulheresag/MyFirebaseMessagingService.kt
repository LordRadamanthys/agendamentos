package com.example.mulheresag

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mulheresag.view.login.LoginActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {



    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val snoozeIntent = Intent(this, LoginActivity::class.java).apply {
            action = "teste"
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)

        var builder = NotificationCompat.Builder(this,"test")
            .setSmallIcon(R.drawable.googleg_disabled_color_18)
            .setContentTitle(p0.data["title"])
            .setContentText(p0.data["text"])
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.googleg_disabled_color_18, getString(R.string.app_name),
                snoozePendingIntent)
            .setAutoCancel(true)



        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            p0.data["id"]?.toInt()?.let { notify(it, builder.build()) }
        }

        println(p0.data["text"])
        Log.e("FOII", p0.data["text"])

    }
}
