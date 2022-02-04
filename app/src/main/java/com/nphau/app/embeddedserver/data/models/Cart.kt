/*
 * Created by nphau on 04/02/2022, 23:02
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 04/02/2022, 23:01
 */

package com.nphau.app.embeddedserver.data.models

import com.nphau.app.embeddedserver.data.Database
import java.util.*

class Cart(val id: String, val items: List<Fruit>) {

    companion object {
        fun sample(): Cart {
            return Cart(
                id = UUID.randomUUID().toString(),
                items = Database.FRUITS
            )
        }
    }
}