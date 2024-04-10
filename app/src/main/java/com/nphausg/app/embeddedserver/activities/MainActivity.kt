/*
 * Created by nphau on 11/19/22, 4:16 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 11/19/22, 3:58 PM
 */

package com.nphausg.app.embeddedserver.activities

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.nphausg.app.embeddedserver.EmbeddedServer
import com.nphausg.app.embeddedserver.R
import com.nphausg.app.embeddedserver.extensions.animateFlash
import com.nphausg.app.embeddedserver.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<AppCompatImageView>(R.id.image_logo).animateFlash()

        // Show IP Address
        findViewById<TextView>(R.id.text_status).apply {
            val simpleTextApi = String.format("GET: %s", EmbeddedServer.host)
            val apiGet = String.format("GET: %s/fruits", EmbeddedServer.host)
            val apiGetWithId =  String.format("GET: %s/fruits/{id}",  EmbeddedServer.host)
            text = String.format(
                "The server is running on this device: %s\n%s\n%s\n%s",
                Build.MODEL,
                simpleTextApi,
                apiGet,
                apiGetWithId
            )
            //animateFlash()
        }

        // Start server
        EmbeddedServer.start()
    }

    override fun onDestroy() {
        //EmbeddedServer.stop()
        super.onDestroy()
    }

}