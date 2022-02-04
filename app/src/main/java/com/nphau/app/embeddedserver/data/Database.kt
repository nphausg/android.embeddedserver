/*
 * Created by nphau on 04/02/2022, 23:00
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 04/02/2022, 23:00
 */

package com.nphau.app.embeddedserver.data

import com.nphau.app.embeddedserver.data.models.Fruit
import java.util.*

object Database {

    val FRUITS = listOf(
        Fruit(UUID.randomUUID().toString(), "Cucumbers 🥒"),
        Fruit(UUID.randomUUID().toString(), "Tomatoes 🍅"),
        Fruit(UUID.randomUUID().toString(), "Orange Juice 🍊")
    )

}