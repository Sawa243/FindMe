package com.example.circlesgame.storages

import android.graphics.Color
import androidx.annotation.Keep
import kotlinx.serialization.Serializable

object SettingsStorage {
    var mainBackgroundColor = Color.WHITE
    var listRecords = Records(mutableListOf())
}

@Serializable
data class Records(val list: MutableList<Int>)