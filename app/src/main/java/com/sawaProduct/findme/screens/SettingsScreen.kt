package com.sawaProduct.findme.screens

import android.app.Activity
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sawaProduct.findme.ComposeVolumeButton
import com.sawaProduct.findme.R
import com.sawaProduct.findme.TextStyle
import com.sawaProduct.findme.ui.theme.*

@Composable
fun SettingsScreen(
    navController: NavController,
    background: Color,
    modifier: Modifier,
    changeBackground: (color: Color) -> Unit,
    randomBackground: () -> Unit,
    music: MediaPlayer
) {
    val view = LocalView.current
    (view.context as Activity).window.statusBarColor = background.toArgb()
    (view.context as Activity).window.navigationBarColor = background.toArgb()

    var blacking by remember {
        mutableStateOf(background == Color.Black)
    }

    var padding by remember {
        mutableStateOf(50.dp)
    }
    var paddingRow by remember {
        mutableStateOf(25.dp)
    }
    var haveHeight by remember {
        mutableStateOf(true)
    }
    BoxWithConstraints(
        modifier = modifier
            .background(background)
            .fillMaxSize()
    ) {
        if (this.maxHeight < 700.dp) {
            padding = 5.dp
            haveHeight = false
            paddingRow = 10.dp
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = padding, bottom = padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.the_background),
                style = TextStyle(blacking),
                fontSize = 40.sp
            )

            Row(
                modifier = modifier.padding(top = paddingRow),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier.padding(end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            changeBackground(White)
                            blacking = false
                        },
                        modifier.size(60.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = White),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {}
                    Text(
                        modifier = modifier.padding(top = 10.dp).width(110.dp),
                        text = stringResource(id = R.string.white),
                        style = TextStyle(blacking)
                    )
                }
                Column(
                    modifier = modifier.padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            changeBackground(Green)
                            blacking = false
                        },
                        modifier.size(60.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Green),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {}
                    Text(
                        modifier = modifier.padding(top = 10.dp).width(110.dp),
                        text = stringResource(id = R.string.green),
                        style = TextStyle(blacking)
                    )
                }
            }

            Row(
                modifier = modifier.padding(top = paddingRow),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier.padding(end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            changeBackground(Color.Black)
                            blacking = true
                        },
                        modifier.size(60.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        border = BorderStroke(1.dp, Color.White)
                    ) {}
                    Text(
                        modifier = modifier.padding(top = 10.dp).width(110.dp),
                        text = stringResource(id = R.string.black),
                        style = TextStyle(blacking)
                    )
                }
                Column(
                    modifier = modifier.padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            changeBackground(Cyan)
                            blacking = false
                        },
                        modifier.size(60.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Cyan),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {}
                    Text(
                        modifier = modifier.padding(top = 10.dp).width(110.dp),
                        text = stringResource(id = R.string.cyan),
                        style = TextStyle(blacking)
                    )
                }
            }

            Row(
                modifier = modifier.padding(top = paddingRow),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier.padding(end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            changeBackground(Yellow)
                            blacking = false
                        },
                        modifier.size(60.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {}
                    Text(
                        modifier = modifier.padding(top = 10.dp).width(110.dp),
                        text = stringResource(id = R.string.yellow),
                        style = TextStyle(blacking)
                    )
                }
                Column(
                    modifier = modifier.padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            randomBackground()
                            blacking = false
                        },
                        modifier.size(60.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Purple40,
                                            Cyan
                                        )
                                    )
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .size(60.dp)
                        )
                    }
                    Text(
                        modifier = modifier.padding(top = 10.dp).width(110.dp),
                        text = stringResource(id = R.string.random),
                        style = TextStyle(blacking)
                    )
                }
            }

            Box(modifier = modifier.padding(5.dp)) {
                ComposeVolumeButton(music, haveHeight)
            }

            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Button(
                    onClick = { navController.navigate("mainScreen") },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = modifier.width(250.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.back_to_menu),
                            style = TextStyle()
                        )
                    }
                }
            }
        }
    }

}