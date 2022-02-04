/*
 * Created by nphau on 04/02/2022, 22:24
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 04/02/2022, 22:23
 */

package com.nphau.app.embeddedserver.utils

import java.net.InetAddress
import java.net.NetworkInterface

object NetworkUtils {

    fun getLocalIpAddress(): String? = getInetAddresses()
        .filter { it.isLocalAddress() }
        .map { it.hostAddress }
        .firstOrNull()

    private fun getInetAddresses() = NetworkInterface.getNetworkInterfaces()
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