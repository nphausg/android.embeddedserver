/*
 * Created by nphau on 04/02/2022, 22:50
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 04/02/2022, 22:50
 */

package com.nphausg.app.embeddedserver.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Fruit(val id: String = "", val name: String = "")