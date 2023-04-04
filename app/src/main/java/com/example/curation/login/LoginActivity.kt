package com.example.curation.login

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.curation.MainActivity
import com.example.curation.R
import com.example.curation.databinding.ActivityLoginBinding
import com.example.curation.utils.Constants
import com.example.curation.notification.NotificationBuilder

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(Constants.TAG, "LoginActivitiy")

        binding.login.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        val notifybuilder: NotificationBuilder = NotificationBuilder()

        //
        notifybuilder.NotificationChannelBuild(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false, getString(R.string.app_name), "App notification channel") // 1

        val channelId = "$packageName-${getString(R.string.app_name)}" // 2
        val title = "Android Developer"
        val content = "Notifications in Android P"

        //val intent = Intent(baseContext, NewActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 3

        val builder = NotificationCompat.Builder(this, channelId)  // 4
        builder.setSmallIcon(R.drawable.round_background)    // 5
        builder.setContentTitle(title)    // 6
        builder.setContentText(content)    // 7
        builder.priority = NotificationCompat.PRIORITY_DEFAULT    // 8
        builder.setAutoCancel(true)   // 9
        builder.setContentIntent(pendingIntent)   // 10

        val notificationManager = NotificationManagerCompat.from(this)
        //notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11



    }
}