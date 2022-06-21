package com.josty.genshin.utils

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use

@ColorInt
fun Context.getThemeColor(
    @AttrRes resId: Int,
    @ColorInt default: Int = Color.TRANSPARENT
) = obtainStyledAttributes(intArrayOf(resId)).use {
    it.getColor(0, default)
}