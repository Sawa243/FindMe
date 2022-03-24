package com.example.circlesgame.pages

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.circlesgame.R
import com.example.circlesgame.popup.ResultsDialogFragment
import com.example.circlesgame.databinding.FragmentGameScreenBinding
import com.example.circlesgame.storages.SettingsStorage
import com.example.circlesgame.storages.SettingsStorage.listRecords
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.random.Random

class GameScreen : Fragment() {

    private var _binding: FragmentGameScreenBinding? = null
    private val binding get() = _binding!!
    private var score = 0
    private var changeAlphaCircle = 0.50f
    private var startCount = 3
    private var counterTimer = 11000L
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            countScore.text = score.toString()
            btnMenu.setOnClickListener {
                addScore(score)
                with(activity?.getPreferences(Context.MODE_PRIVATE)?.edit()) {
                    this?.putString("RECORDS_USER", Json.encodeToString(listRecords))
                    this?.apply()
                }
                findNavController().navigate(R.id.action_GameScreen_to_MainScreen)
            }
        }
        createCircle()
        startTimeCounter()
    }

    private fun correctAnswer() {

        if (startCount < 78) startCount += 3
        else startCount = 80
        if (changeAlphaCircle != 0.85f) changeAlphaCircle += 0.01f
        createCircle()
        score += 100
        timer.apply {
            cancel()
            start()
        }
        binding.countScore.text = score.toString()
    }

    private fun notCorrectAnswer() {
        timer.cancel()
        ResultsDialogFragment(score) { restartGame() }.show(
            childFragmentManager,
            ResultsDialogFragment.TAG
        )
    }

    private fun restartGame() {
        startCount = 3
        changeAlphaCircle = 0.30f
        createCircle()
        addScore(score)
        score = 0
        binding.countScore.text = score.toString()
        timer.apply {
            cancel()
            start()
        }
    }

    private fun createCircle() {
        binding.gameCircles.apply {
            deleteAllCircle()
            callbackPositive = { correctAnswer() }
            callbackNegative = { notCorrectAnswer() }
            startCountCircle = startCount
            startColor = generateColor()
            changeAlpha = changeAlphaCircle
            start()
        }
    }

    private fun startTimeCounter() {
        timer = object : CountDownTimer(counterTimer, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = " ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                notCorrectAnswer()
            }
        }.start()
    }

    private fun generateColor(): Int {
        val random = Random.Default
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    private fun addScore(score: Int) {
        if (score != 0 && !listRecords.list.contains(score)) {
            if (listRecords.list.size < 8) {
                listRecords.list.add(score)
            } else {
                listRecords.list.sortBy { it }
                listRecords.list[0] = score
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
        _binding = null
    }

}