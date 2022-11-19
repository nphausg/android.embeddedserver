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
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.gzip
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.websocket.WebSockets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PORT = 5001
    }

    private val server by lazy {
        embeddedServer(Netty, PORT, watchPaths = emptyList()) {
            install(WebSockets)
            install(CallLogging)
            // provides the automatic content conversion of requests based on theirContent-Type
            // and Accept headers. Together with the json() setting, this enables automatic
            // serialization and deserialization to and from JSON – allowing
            // us to delegate this tedious task to the framework.
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }
            // configures Cross-Origin Resource Sharing. CORS is needed to make calls from arbitrary
            // JavaScript clients, and helps us prevent issues down the line.
            install(CORS) {
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                method(HttpMethod.Delete)
                anyHost()
            }
            // Greatly reduces the amount of data that's needed to be sent to the client by
            // gzipping outgoing content when applicable.
            install(Compression) {
                gzip()
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