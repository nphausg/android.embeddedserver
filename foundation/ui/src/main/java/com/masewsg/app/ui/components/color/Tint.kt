/*
 * Created by nphau on 5/4/24, 10:36 PM
 * Copyright (c) 2024 . All rights reserved.
 * Last modified 4/10/24, 7:59 PM
 */

package com.masewsg.app.ui.components.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A class to model background color and tonal elevation values for Now in Android.
 */
@Immutable
data class TintTheme(
    val iconTint: Color = Color.Unspecified,
)

/**
 * A composition local for [TintTheme].
 */
val LocalTintTheme = staticCompositionLocalOf { TintTheme() }
