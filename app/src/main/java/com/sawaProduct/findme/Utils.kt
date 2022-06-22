package com.sawaProduct.findme

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sawaProduct.findme.storages.SettingsStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.random.Random

fun TextStyle(blacking: Boolean = false): TextStyle {
    return TextStyle(
        color = if (blacking) {
            Color.White
        } else {
            Color.Black
        },
        fontSize = 24.sp,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp
    )
}

fun generateColor(): Int {
    val random = Random.Default
    return android.graphics.Color.argb(
        255,
        random.nextInt(256),
        random.nextInt(256),
        random.nextInt(256)
    )
}

fun addScore(score: Int, context: Context) {
    with(SettingsStorage.listRecords) {
        if (score != 0 && !list.contains(score)) {
            if (list.size < 8) {
                list.add(score)
                list.sortBy { it }
            } else {
                list.sortBy { it }
                list[0] = score
            }

            with(context.getActivity()?.getPreferences(Context.MODE_PRIVATE)?.edit()) {
                this?.putString("RECORDS_USER", Json.encodeToString(SettingsStorage.listRecords))
                this?.apply()
            }
        }
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}