/*
 * Created by nphau on 4/13/24, 11:46 AM
 * Copyright (c) 2024 . All rights reserved.
 * Last modified 4/13/24, 11:46 AM
 */

package com.nphausg.app.embeddedserver.utils

import kotlinx.serialization.json.Json

object FileUtils {
    fun readText(path: String): String =
        this::class.java.classLoader?.getResource(path)?.readText().orEmpty()

    inline fun <reified T> decode(json: String): T =
        Json.decodeFromString<T>(json)

    inline fun <reified T> readJson(path: String): T =
        decode(readText(path))
}


