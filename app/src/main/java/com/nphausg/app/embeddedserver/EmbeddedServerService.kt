package com.nphausg.app.embeddedserver.service

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.nphausg.app.embeddedserver.EmbeddedServer
import com.nphausg.app.embeddedserver.R

class EmbeddedServerService : Service() {

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
        EmbeddedServer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        EmbeddedServer.stop()
    }

    private fun startForegroundService() {
        val channelId = "embedded_server_channel"
        val channelName = "Embedded Server Service"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Embedded Server Running")
            .setContentText("Your server is running in the background.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
