package com.sawaProduct.findme.storages

import com.sawaProduct.findme.ui.theme.Purple40
import kotlinx.serialization.Serializable

object SettingsStorage {
    var mainBackgroundColor = Purple40
    var listRecords = Records(mutableListOf())
    var musicVolume = 0.5f
}

@Serializable
data class Records(val list: MutableList<Int>)