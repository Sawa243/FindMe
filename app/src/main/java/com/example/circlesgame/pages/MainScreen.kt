package com.example.circlesgame.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.circlesgame.R
import com.example.circlesgame.databinding.FragmentMainScreenBinding
import com.example.circlesgame.storages.SettingsStorage
import kotlin.system.exitProcess

class MainScreen : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            MainScreen.background = SettingsStorage.mainBackgroundColor.toDrawable()
            buttonStart.setOnClickListener {
                findNavController().navigate(R.id.action_MainScreen_to_GameScreen)
            }
            buttonSettings.setOnClickListener {
                findNavController().navigate(R.id.action_MainScreen_to_settingsScreen)
            }
            buttonExit.setOnClickListener{
                exitProcess(-1)
            }
            buttonRecords.setOnClickListener{
                findNavController().navigate(R.id.action_MainScreen_to_recordsScreen)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}