package com.example.circlesgame.pages

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.circlesgame.R
import com.example.circlesgame.databinding.FragmentRecordsScreenBinding
import com.example.circlesgame.storages.SettingsStorage

class RecordsScreen : Fragment() {

    private var _binding: FragmentRecordsScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecordsScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            root.background = SettingsStorage.mainBackgroundColor.toDrawable()
            btnMenu.setOnClickListener {
                findNavController().navigate(R.id.action_recordsScreen_to_MainScreen)
            }
        }
        fillRecords()
    }

    private fun fillRecords() {
        SettingsStorage.listRecords.list.sortedDescending().forEach { record ->
            createTextRecord(record.toString())
        }
    }

    private fun createTextRecord(record: String) {
        binding.pageRecords.addView(TextView(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(20)
            text = record
            setTextColor(context.getColor(R.color.Black))
            textSize = 24F
            foregroundGravity = Gravity.CENTER

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}