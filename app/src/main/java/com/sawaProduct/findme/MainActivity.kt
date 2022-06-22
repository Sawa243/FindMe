package com.sawaProduct.findme

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sawaProduct.findme.screens.GameScreen
import com.sawaProduct.findme.screens.MainScreen
import com.sawaProduct.findme.screens.RecordScreen
import com.sawaProduct.findme.screens.SettingsScreen
import com.sawaProduct.findme.storages.SettingsStorage
import com.sawaProduct.findme.ui.theme.FindMeTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private lateinit var sharedPref: SharedPreferences
    private val gsonRecords = Json.encodeToString(SettingsStorage.listRecords)
    private lateinit var music: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        SettingsStorage.apply {
            mainBackgroundColor = Color(sharedPref.getInt("BACKGROUND_COLOR", White.toArgb()))
            musicVolume = sharedPref.getFloat("MUSIC_VOLUME", 0.5f)
            val recordsInJson = sharedPref.getString("RECORDS_USER", gsonRecords)
            recordsInJson?.let { listRecords = Json.decodeFromString(it) }
        }

        music = MediaPlayer.create(applicationContext, R.raw.bensound)
        music.apply {
            isLooping = true
            start()
            setVolume(SettingsStorage.musicVolume, SettingsStorage.musicVolume)
        }

        setContent {
            App()
        }
    }

    @Preview
    @Composable
    fun App() {
        FindMeTheme {

            val modifier = Modifier
            val navController = rememberNavController()
            var stateBackground by remember {
                mutableStateOf(SettingsStorage.mainBackgroundColor)
            }

            NavHost(navController = navController, startDestination = "mainScreen") {
                composable("mainScreen") {
                    MainScreen(modifier, stateBackground, navController)
                }
                composable("gameScreen") {
                    GameScreen(
                        navController,
                        stateBackground,
                        modifier,
                        viewModel()
                    )
                }
                composable("recordScreen") {
                    RecordScreen(
                        navController,
                        stateBackground,
                        modifier
                    )
                }
                composable("settingsScreen") {
                    SettingsScreen(
                        navController,
                        stateBackground,
                        modifier,
                        {
                            stateBackground = it
                            SettingsStorage.mainBackgroundColor = stateBackground
                        },
                        {
                            stateBackground = Color(generateColor())
                            SettingsStorage.mainBackgroundColor = stateBackground
                        },
                        music
                    )
                }
            }
        }
    }

    override fun onStop() {
        music.stop()
        with(sharedPref.edit()) {
            putInt("BACKGROUND_COLOR", SettingsStorage.mainBackgroundColor.toArgb())
            putString(
                "RECORDS_USER",
                Json.encodeToString(SettingsStorage.listRecords)
            )
            putFloat("MUSIC_VOLUME", SettingsStorage.musicVolume)
            apply()
        }
        super.onStop()
    }

}