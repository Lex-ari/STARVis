package com.broncospace.android.starvis

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.R

const val NOTIFICATION_CHANNEL_ID = "N2YO_poll"
class STARVisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SatelliteRepository.initialize(this)
        //PreferencesRepository.initialize(this)

        //Check to see if android OREO or greater, then adds notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}