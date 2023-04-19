package com.example.notions.contract

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface HasCustomAction {

    fun getCustomAction(): List<CustomAction>

}

class CustomAction(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int,
    val onCustomAction: Runnable
)