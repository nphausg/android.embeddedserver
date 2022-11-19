/*
 * Created by nphau on 04/02/2022, 23:14
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 04/02/2022, 22:29
 */

package com.nphausg.app.embeddedserver.extensions

import android.animation.ObjectAnimator
import android.view.View

fun View.animateFlash(duration: Long = 1_000L) =
    ObjectAnimator.ofFloat(this, "alpha", 1f, 0.2f, 1f).apply {
        this.duration = duration
        repeatCount = ObjectAnimator.INFINITE
        start()
    }