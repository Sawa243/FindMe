package com.sawaProduct.findme

import android.media.MediaPlayer
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sawaProduct.findme.storages.SettingsStorage
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@Composable
fun ComposeVolumeButton(music: MediaPlayer, haveHeight: Boolean) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Red, RoundedCornerShape(10.dp))
                .padding(if (haveHeight) 20.dp else 10.dp)
        ) {
            var volume by remember { mutableStateOf(SettingsStorage.musicVolume * 10) }
            val barCount = 20

            MusicVolume(
                modifier = Modifier.size(if (haveHeight) 100.dp else 60.dp)
            ) {
                volume = it
                music.setVolume(it / 10, it / 10)
                SettingsStorage.musicVolume = it / 10
            }

            Spacer(modifier = Modifier.width(20.dp))

            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (haveHeight) 80.dp else 45.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicVolume(
    modifier: Modifier = Modifier,
    limitInAngle: Float = 25f,
    onValueChange: (Float) -> Unit
) {
    var rotation by remember { mutableStateOf(limitInAngle) }
    var touchX by remember { mutableStateOf(0f) }
    var touchY by remember { mutableStateOf(0f) }
    var centerX by remember { mutableStateOf(0f) }
    var centerY by remember { mutableStateOf(0f) }

    Image(
        painter = painterResource(id = R.drawable.music_bar),
        contentDescription = "Volume Image",
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitInAngle..limitInAngle) {
                            val fixedAngle = if (angle in -180f..-limitInAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitInAngle) / (360f - 2 * limitInAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember { constraints.maxWidth / (2f * barCount) }

        Canvas(
            modifier = modifier
        ) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Red else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}