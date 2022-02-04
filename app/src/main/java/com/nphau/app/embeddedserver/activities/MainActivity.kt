/*
 * Created by nphau on 04/02/2022, 22:24
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 04/02/2022, 22:23
 */

package com.nphau.app.embeddedserver.activities

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.nphau.app.embeddedserver.R
import com.nphau.app.embeddedserver.data.BaseResponse
import com.nphau.app.embeddedserver.data.Database
import com.nphau.app.embeddedserver.data.models.Cart
import com.nphau.app.embeddedserver.extensions.animateFlash
import com.nphau.app.embeddedserver.utils.NetworkUtils
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.websocket.*
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