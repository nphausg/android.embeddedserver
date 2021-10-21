package com.imstudio.app.embeddedserver

import java.net.InetAddress
import java.net.NetworkInterface

object NetworkUtils {

    fun getLocalIpAddress(): String? = getInetAddresses()
        .filter { it.isLocalAddress() }
        .map { it.hostAddress }
        .firstOrNull()

    fun getInetAddresses() = NetworkInterface.getNetworkInterfaces()
        .iterator()
        .asSequence()
        .flatMap { networkInterface ->
            networkInterface.inetAddresses
                .asSequence()
                .filter { !it.isLoopbackAddress }
        }.toList()
}

fun InetAddress.isLocalAddress(): Boolean {
    try {
        return isSiteLocalAddress
                && !hostAddress!!.contains(":")
                && hostAddress != "127.0.0.1"
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}