/*
 * Created by nphau on 11/19/22, 4:16 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 11/19/22, 3:58 PM
 */

package com.nphausg.app.embeddedserver.data

import com.nphausg.app.embeddedserver.data.models.Fruit
import java.util.UUID

object Database {

    val FRUITS = listOf(
        Fruit(UUID.randomUUID().toString(), "Cucumbers 🥒"),
        Fruit(UUID.randomUUID().toString(), "Tomatoes 🍅"),
        Fruit(UUID.randomUUID().toString(), "Orange Juice 🍊")
    )

}