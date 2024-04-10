/*
 * Created by nphau on 4/10/24, 8:33 PM
 * Copyright (c) 2024 . All rights reserved.
 * Last modified 4/10/24, 8:33 PM
 */

package com.nphausg.app.ui.components.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nphausg.app.ui.components.BackgroundTheme
import com.nphausg.app.ui.components.LocalBackgroundTheme
import com.nphausg.app.ui.components.color.Blue10
import com.nphausg.app.ui.components.color.Blue20
import com.nphausg.app.ui.components.color.Blue30
import com.nphausg.app.ui.components.color.Blue40
import com.nphausg.app.ui.components.color.Blue80
import com.nphausg.app.ui.components.color.Blue90
import com.nphausg.app.ui.components.color.DarkGreen10
import com.nphausg.app.ui.components.color.DarkGreen20
import com.nphausg.app.ui.components.color.DarkGreen30
import com.nphausg.app.ui.components.color.DarkGreen40
import com.nphausg.app.ui.components.color.DarkGreen80
import com.nphausg.app.ui.components.color.DarkGreen90
import com.nphausg.app.ui.components.color.DarkGreenGray10
import com.nphausg.app.ui.components.color.DarkGreenGray20
import com.nphausg.app.ui.components.color.DarkGreenGray90
import com.nphausg.app.ui.components.color.DarkGreenGray95
import com.nphausg.app.ui.components.color.DarkGreenGray99
import com.nphausg.app.ui.components.color.DarkPurpleGray10
import com.nphausg.app.ui.components.color.DarkPurpleGray20
import com.nphausg.app.ui.components.color.DarkPurpleGray90
import com.nphausg.app.ui.components.color.DarkPurpleGray95
import com.nphausg.app.ui.components.color.DarkPurpleGray99
import com.nphausg.app.ui.components.color.GradientColors
import com.nphausg.app.ui.components.color.Green10
import com.nphausg.app.ui.components.color.Green20
import com.nphausg.app.ui.components.color.Green30
import com.nphausg.app.ui.components.color.Green40
import com.nphausg.app.ui.components.color.Green80
import com.nphausg.app.ui.components.color.Green90
import com.nphausg.app.ui.components.color.GreenGray30
import com.nphausg.app.ui.components.color.GreenGray50
import com.nphausg.app.ui.components.color.GreenGray60
import com.nphausg.app.ui.components.color.GreenGray80
import com.nphausg.app.ui.components.color.GreenGray90
import com.nphausg.app.ui.components.color.LocalGradientColors
import com.nphausg.app.ui.components.color.LocalTintTheme
import com.nphausg.app.ui.components.color.Orange10
import com.nphausg.app.ui.components.color.Orange20
import com.nphausg.app.ui.components.color.Orange30
import com.nphausg.app.ui.components.color.Orange40
import com.nphausg.app.ui.components.color.Orange80
import com.nphausg.app.ui.components.color.Orange90
import com.nphausg.app.ui.components.color.Purple10
import com.nphausg.app.ui.components.color.Purple20
import com.nphausg.app.ui.components.color.Purple30
import com.nphausg.app.ui.components.color.Purple40
import com.nphausg.app.ui.components.color.Purple80
import com.nphausg.app.ui.components.color.Purple90
import com.nphausg.app.ui.components.color.PurpleGray30
import com.nphausg.app.ui.components.color.PurpleGray50
import com.nphausg.app.ui.components.color.PurpleGray60
import com.nphausg.app.ui.components.color.PurpleGray80
import com.nphausg.app.ui.components.color.PurpleGray90
import com.nphausg.app.ui.components.color.Red10
import com.nphausg.app.ui.components.color.Red20
import com.nphausg.app.ui.components.color.Red30
import com.nphausg.app.ui.components.color.Red40
import com.nphausg.app.ui.components.color.Red80
import com.nphausg.app.ui.components.color.Red90
import com.nphausg.app.ui.components.color.Teal10
import com.nphausg.app.ui.components.color.Teal20
import com.nphausg.app.ui.components.color.Teal30
import com.nphausg.app.ui.components.color.Teal40
import com.nphausg.app.ui.components.color.Teal80
import com.nphausg.app.ui.components.color.Teal90
import com.nphausg.app.ui.components.color.TintTheme
import com.nphausg.app.ui.components.typography.Typography

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Purple90,
    onPrimaryContainer = Purple10,
    secondary = Orange40,
    onSecondary = Color.White,
    secondaryContainer = Orange90,
    onSecondaryContainer = Orange10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkPurpleGray99,
    onBackground = DarkPurpleGray10,
    surface = DarkPurpleGray99,
    onSurface = DarkPurpleGray10,
    surfaceVariant = PurpleGray90,
    onSurfaceVariant = PurpleGray30,
    inverseSurface = DarkPurpleGray20,
    inverseOnSurface = DarkPurpleGray95,
    outline = PurpleGray50,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple20,
    primaryContainer = Purple30,
    onPrimaryContainer = Purple90,
    secondary = Orange80,
    onSecondary = Orange20,
    secondaryContainer = Orange30,
    onSecondaryContainer = Orange90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkPurpleGray10,
    onBackground = DarkPurpleGray90,
    surface = DarkPurpleGray10,
    onSurface = DarkPurpleGray90,
    surfaceVariant = PurpleGray30,
    onSurfaceVariant = PurpleGray80,
    inverseSurface = DarkPurpleGray90,
    inverseOnSurface = DarkPurpleGray10,
    outline = PurpleGray60,
)

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    secondaryContainer = DarkGreen90,
    onSecondaryContainer = DarkGreen10,
    tertiary = Teal40,
    onTertiary = Color.White,
    tertiaryContainer = Teal90,
    onTertiaryContainer = Teal10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkGreenGray99,
    onBackground = DarkGreenGray10,
    surface = DarkGreenGray99,
    onSurface = DarkGreenGray10,
    surfaceVariant = GreenGray90,
    onSurfaceVariant = GreenGray30,
    inverseSurface = DarkGreenGray20,
    inverseOnSurface = DarkGreenGray95,
    outline = GreenGray50,
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = Green80,
    onPrimary = Green20,
    primaryContainer = Green30,
    onPrimaryContainer = Green90,
    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    secondaryContainer = DarkGreen30,
    onSecondaryContainer = DarkGreen90,
    tertiary = Teal80,
    onTertiary = Teal20,
    tertiaryContainer = Teal30,
    onTertiaryContainer = Teal90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkGreenGray10,
    onBackground = DarkGreenGray90,
    surface = DarkGreenGray10,
    onSurface = DarkGreenGray90,
    surfaceVariant = GreenGray30,
    onSurfaceVariant = GreenGray80,
    inverseSurface = DarkGreenGray90,
    inverseOnSurface = DarkGreenGray10,
    outline = GreenGray60,
)

/**
 * Light Android gradient colors
 */
val LightAndroidGradientColors = GradientColors(container = DarkGreenGray95)

/**
 * Dark Android gradient colors
 */
val DarkAndroidGradientColors = GradientColors(container = Color.Black)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkGreenGray95)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
fun ImsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }
    // Gradient colors
    val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )
    val gradientColors = when {
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }
    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
