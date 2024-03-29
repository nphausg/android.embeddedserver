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
import com.nphausg.app.embeddedserver.R
import com.nphausg.app.embeddedserver.data.BaseResponse
import com.nphausg.app.embeddedserver.data.Database
import com.nphausg.app.embeddedserver.data.models.Cart
import com.nphausg.app.embeddedserver.extensions.animateFlash
import com.nphausg.app.embeddedserver.utils.NetworkUtils
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PORT = 6868
    }

    private val server by lazy {
        embeddedServer(Netty, PORT, watchPaths = emptyList()) {
            // configures Cross-Origin Resource Sharing. CORS is needed to make calls from arbitrary
            // JavaScript clients, and helps us prevent issues down the line.
            install(CORS) {
                anyHost()
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            routing {
                get("/") {
                    call.respondText(
                        text = "Hello!! You are here in ${Build.MODEL}",
                        contentType = ContentType.Text.Plain
                    )
                }
                get("/fruits") {
                    call.respond(HttpStatusCode.OK, BaseResponse(Cart.sample()))
                }
                get("/fruits/{id}") {
                    val id = call.parameters["id"]
                    val fruit = Database.FRUITS.find { it.id == id }
                    if (fruit != null) {
                        call.respond(HttpStatusCode.OK, BaseResponse(fruit))
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatImageView>(R.id.image_logo).animateFlash()

        // Show IP Address
        findViewById<TextView>(R.id.text_status).apply {
            val simpleTextApi =
                String.format("GET: %s:%d", NetworkUtils.getLocalIpAddress(), PORT)
            val apiGet =
                String.format("GET: %s:%d/fruits", NetworkUtils.getLocalIpAddress(), PORT)
            val apiGetWithId =
                String.format("GET: %s:%d/fruits/{id}", NetworkUtils.getLocalIpAddress(), PORT)
            text = String.format("%s\n%s\n%s", simpleTextApi, apiGet, apiGetWithId)
            //animateFlash()
        }

        // Start server
        CoroutineScope(Dispatchers.IO).launch {
            server.start(wait = true)
        }
    }

    override fun onDestroy() {
        server.stop(1_000, 2_000)
        super.onDestroy()
    }

}