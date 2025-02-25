/*
 * Created by nphau on 2/13/25, 9:45 AM
 * Copyright (c) 2025 . All rights reserved.
 * Last modified 2/13/25, 9:45 AM
 */

package com.nphausg.app.embeddedserver.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.masewsg.app.ui.components.ThemePreviews
import com.nphausg.app.embeddedserver.R
import kotlinx.coroutines.launch

@Composable
internal fun AnimatedLogo() {

    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    Card(
        modifier = Modifier
            .size(128.dp)
            .offset {
                IntOffset(
                    offsetX.value.toInt(),
                    offsetY.value.toInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            offsetY.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(
                                    durationMillis = 1000,
                                    delayMillis = 0
                                )
                            )
                        }
                        coroutineScope.launch {
                            offsetX.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(
                                    durationMillis = 1000,
                                    delayMillis = 0
                                )
                            )
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        coroutineScope.launch {
                            offsetY.snapTo(offsetY.value + dragAmount.y)
                        }
                        coroutineScope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            },
        shape = CircleShape
    ) {
        Image(
            painterResource(R.drawable.logo),
            contentDescription = "",
            contentScale = ContentScale.Inside
        )
    }
}

@Composable
@ThemePreviews
private fun AnimatedLogoPreview() {
    AnimatedLogo()
}