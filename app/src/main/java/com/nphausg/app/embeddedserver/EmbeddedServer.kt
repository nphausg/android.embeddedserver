/*
 * Created by nphau on 4/10/24, 7:04 PM
 * Copyright (c) 2024 . All rights reserved.
 * Last modified 4/10/24, 7:04 PM
 */

package com.nphausg.app.embeddedserver

import android.os.Build
import com.nphausg.app.embeddedserver.data.BaseResponse
import com.nphausg.app.embeddedserver.data.Database
import com.nphausg.app.embeddedserver.data.models.Cart
import com.nphausg.app.embeddedserver.utils.NetworkUtils
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object EmbeddedServer {

    private const val PORT = 6868
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val server by lazy {
        embeddedServer(Netty, PORT) {
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
                    okText(call, "Hello!! You are here in ${Build.MODEL}")
                }
                get("/fruits") {
                    okText(call, Json.encodeToString(BaseResponse(Cart.sample()).also {
                        println(it.data)
                    }))
                }
                get("/fruits/{id}") {
                    val id = call.parameters["id"]
                    val fruit = Database.FRUITS.find { it.id == id }
                    if (fruit != null) {
                        okText(call, Json.encodeToString(fruit))
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }
        }
    }

    fun start() {
        ioScope.launch {
            server.start(wait = true)
        }
    }

    fun stop() {
        server.stop(1_000, 2_000)
    }

    val host: String
        get() = String.format("%s:%d", NetworkUtils.getLocalIpAddress(), PORT)

    suspend fun okText(call: ApplicationCall, text: String) {
        call.respondText(
            text = text,
            status = HttpStatusCode.OK,
            contentType = ContentType.Application.Json
        )
    }
}