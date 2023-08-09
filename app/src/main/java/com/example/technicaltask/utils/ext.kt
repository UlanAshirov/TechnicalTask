package com.example.technicaltask.utils

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible

fun View.visible() {
    this.isVisible = true
}

fun View.gone() {
    this.isGone = true
}