package com.example.circlesgame.pages

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.circlesgame.R
import com.example.circlesgame.databinding.FragmentSettingsScreenBinding
import com.example.circlesgame.storages.SettingsStorage
import kotlin.random.Random

class SettingsScreen : Fragment() {

    private var _binding: FragmentSettingsScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackgroundColorButtons()

        binding.apply {
            root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            btnMenu.setOnClickListener {
                with(requireActivity().getPreferences(Context.MODE_PRIVATE).edit()) {
                    this.putInt("BACKGROUND_COLOR", SettingsStorage.mainBackgroundColor)
                    this.apply()
                }
                findNavController().navigate(R.id.action_settingsScreen_to_MainScreen)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBackgroundColorButtons() {
        with(binding) {
            buttonWhite.setOnClickListener {
                SettingsStorage.mainBackgroundColor = Color.WHITE
                root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            }
            buttonGreen.setOnClickListener {
                SettingsStorage.mainBackgroundColor = Color.GREEN
                root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            }
            buttonCyan.setOnClickListener {
                SettingsStorage.mainBackgroundColor = Color.CYAN
                root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            }
            buttonYellow.setOnClickListener {
                SettingsStorage.mainBackgroundColor = Color.YELLOW
                root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            }
            buttonGray.setOnClickListener {
                SettingsStorage.mainBackgroundColor = Color.GRAY
                root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            }
            buttonRandom.setOnClickListener {
                SettingsStorage.mainBackgroundColor = generateColor()
                root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            }
        }
    }

    private fun generateColor(): Int {
        val random = Random.Default
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

}