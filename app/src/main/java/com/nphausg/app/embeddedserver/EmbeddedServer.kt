package com.nphausg.app.embeddedserver

import android.os.Build
import android.telephony.SmsManager
import com.nphausg.app.embeddedserver.data.Database
import com.nphausg.app.embeddedserver.data.models.Cart
import com.nphausg.app.embeddedserver.utils.FileUtils
import com.nphausg.app.embeddedserver.utils.NetworkUtils
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.response.respondFile
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object EmbeddedServer {

    private const val PORT = 6868
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private const val FILE_NAME = "file.jpg"
    private var server: ApplicationEngine? = null

    fun start() {
        ioScope.launch {
            try {
                if (server == null) {
                    server = embeddedServer(Netty, PORT) {
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
                            staticResources("/static", "") {
                                default("index.html")
                            }
                            get("/") {
                                okText(call, "Hello!! You are here in ${Build.MODEL}")
                            }
                            get("/fruits") {
                                okText(call, FileUtils.readText("data.json").also {
                                    Database.FRUITS.addAll(FileUtils.decode<Cart>(it).items)
                                })
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
                            get("/download") {
                                val file = File("files/$FILE_NAME")
                                call.response.header(
                                    HttpHeaders.ContentDisposition,
                                    ContentDisposition.Attachment.withParameter(
                                        key = ContentDisposition.Parameters.FileName,
                                        value = FILE_NAME
                                    ).toString()
                                )
                                call.response.status(HttpStatusCode.OK)
                                call.respondFile(file)
                            }
                            post("/send_sms") {
                                val smsRequest = call.receive<SmsRequest>()
                                try {
                                    val smsManager: SmsManager = SmsManager.getDefault()
                                    smsManager.sendTextMessage(smsRequest.phoneNumber, null, smsRequest.message, null, null)
                                    call.respond(HttpStatusCode.OK, "SMS Sent to ${smsRequest.phoneNumber}")
                                } catch (e: Exception) {
                                    call.respond(HttpStatusCode.BadRequest, "Error sending SMS: $e")
                                }
                            }
                        }
                    }
                }
                server?.start(wait = false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun stop() {
        try {
            server?.stop(1_000, 2_000)
            server = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val host: String
        get() = String.format("%s:%d", NetworkUtils.getLocalIpAddress(), PORT)

    private suspend fun okText(call: ApplicationCall, text: String) {
        call.respondText(
            text = text,
            status = HttpStatusCode.OK,
            contentType = ContentType.Application.Json
        )
    }
}

@Serializable
data class SmsRequest(
    val phoneNumber: String,
    val message: String
)
