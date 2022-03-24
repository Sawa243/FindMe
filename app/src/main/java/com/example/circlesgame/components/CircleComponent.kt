package com.example.circlesgame.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.circlesgame.databinding.ComponentCirclesBinding
import com.google.android.material.button.MaterialButton

class CircleComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var _binding: ComponentCirclesBinding =
        ComponentCirclesBinding.inflate(LayoutInflater.from(context), this, true)

    var startColor = Color.argb(255, 255, 255, 255)
    var startCountCircle = 3
    var standardAlpha = 1f
    var changeAlpha = 0.30f
    var callbackNegative: (() -> Unit)? = null
    var callbackPositive: (() -> Unit)? = null

    fun start() {
        var i = 0
        val randomCircle = (0 until startCountCircle).random()
        _binding.parentLiner.columnCount = when (startCountCircle) {
            in 25..27 -> 3
            in 1..12 -> 4
            in 13..15 -> 5
            in 16..18, in 28..60 -> 6
            in 19..21, in 61..70 -> 7
            in 22..24, in 71..80 -> 8
            else -> 8

        }
        while (i < startCountCircle) {
            if (i == randomCircle) _binding.parentLiner.addView(
                createStandardButton(
                    startColor,
                    changeAlpha,
                    callbackPositive
                )
            ) else _binding.parentLiner.addView(createStandardButton())
            i++
        }
    }

    fun deleteAllCircle() {
        _binding.parentLiner.apply {
            removeAllViews()
            columnCount = 3
        }
    }

    private fun createStandardButton(
        color: Int = startColor,
        newAlpha: Float = standardAlpha,
        callback: (() -> Unit)? = callbackNegative
    ): MaterialButton {
        return MaterialButton(context).apply {
            layoutParams = LayoutParams(120, 120)
            insetTop = 0
            insetBottom = 0
            alpha = newAlpha
            cornerRadius = 90
            setBackgroundColor(color)
            setOnClickListener { callback?.invoke() }
        }
    }

}