/*
 * Created by nphau on 4/10/24, 8:59 PM
 * Copyright (c) 2024 . All rights reserved.
 * Last modified 4/10/24, 8:58 PM
 */

package com.nphausg.app.ui.components.color

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
