package com.sawaProduct.findme.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sawaProduct.findme.R
import com.sawaProduct.findme.TextStyle
import com.sawaProduct.findme.addScore
import com.sawaProduct.findme.generateColor
import com.sawaProduct.findme.ui.theme.Cyan
import com.sawaProduct.findme.ui.theme.Pink80
import com.sawaProduct.findme.viewModel.GameViewModel

@Composable
fun GameScreen(
    navController: NavController, stateBackground: Color, modifier: Modifier,
    viewModel: GameViewModel
) {
    val context = LocalContext.current
    var score by remember {
        mutableStateOf(0)
    }

    var visiblePopup by remember {
        mutableStateOf(false)
    }
    val blacking by remember {
        mutableStateOf(stateBackground == Color.Black)
    }

    val lostGame by viewModel.lost.observeAsState(0)

    var startCountCircle by remember { mutableStateOf(3) }
    var changeAlpha by remember { mutableStateOf(0.50f) }
    val randomCircle = (0 until startCountCircle).random()

    fun correctAnswer() {
        if (startCountCircle < 78) startCountCircle += 3 else startCountCircle = 80
        if (changeAlpha != 0.85f) changeAlpha += 0.01f
        score += 100
        viewModel.startCountDown()
    }

    fun notCorrectAnswer() {
        viewModel.cancelTimer()
        visiblePopup = true

    }

    if (lostGame == 1) {
        notCorrectAnswer()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(stateBackground)
    ) {
        Column(
            modifier = modifier
                .padding(top = 20.dp, bottom = 80.dp, end = 20.dp, start = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        addScore(score, context)
                        score = 0
                        navController.navigate("mainScreen")
                    },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.menu),

                            style = TextStyle()
                        )
                    }
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.score), style = TextStyle(blacking),
                        modifier = modifier.padding(end = 20.dp)
                    )
                    Text(text = score.toString(), style = TextStyle(blacking))
                }
            }
            Row(
                modifier = modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.timer),
                    style = TextStyle(blacking),
                    modifier = modifier.padding(end = 5.dp)
                )
                TimerText(viewModel(), blacking)
            }

            GridGame(
                modifier,
                { correctAnswer() },
                { notCorrectAnswer() },
                startCountCircle,
                changeAlpha,
                randomCircle
            )

            /*Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                AndroidView(
                    factory = { context ->
                        BannerAdView(context).apply {
                            val adRequest = AdRequest.Builder().build()
                            setAdUnitId("R-M-DEMO-interstitial") // (R.string.admob_banner_id)
                            setAdSize(AdSize.BANNER_320x50)
                            setBannerAdEventListener(object : BannerAdEventListener {
                                override fun onAdLoaded() {

                                }

                                override fun onAdFailedToLoad(p0: AdRequestError) {

                                }

                                override fun onAdClicked() {

                                }

                                override fun onLeftApplication() {

                                }

                                override fun onReturnedToApplication() {

                                }

                                override fun onImpression(p0: ImpressionData?) {

                                }

                            })
                            loadAd(adRequest)
                        }
                    },
                )

            }*/

        }
    }

    AnimatedVisibility(
        visible = visiblePopup,
        enter = expandHorizontally() + fadeIn(),
        exit = shrinkHorizontally() + fadeOut()
    ) {
        PopUpResults(score, blacking) {
            addScore(score, context)
            score = 0
            visiblePopup = !visiblePopup
            startCountCircle = 3
            viewModel.startCountDown()
        }
    }
}

@Composable
fun GridGame(
    modifier: Modifier,
    correctAnswer: () -> Unit,
    notCorrectAnswer: () -> Unit,
    startCountCircle: Int,
    changeAlpha: Float,
    randomCircle: Int
) {
    var startColor by remember { mutableStateOf(generateColor()) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val gridHeight = screenHeight - 200.dp
    val gridWidth = screenWidth - 20.dp
    val minSize: Dp = when (startCountCircle) {
        3, 6, 9 -> gridWidth / 3 - 15.dp
        12, 18, 27 -> gridWidth / 4 - 25.dp
        15, 21, 24, 30, 39 -> gridWidth / 5 - 15.dp
        33, 36, 45 -> gridWidth / 6 - 25.dp
        42, 48, 52, 55 -> gridWidth / 7 - 15.dp
        else -> 40.dp
    }

    fun correctAnswer() {
        startColor = generateColor()
        correctAnswer.invoke()
    }

    fun notCorrectAnswer() {
        notCorrectAnswer.invoke()
    }

    LazyVerticalGrid(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp)
            .height(gridHeight)
            .width(gridWidth),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(1.dp),
        columns = GridCells.Adaptive(minSize)
    ) {
        items(startCountCircle) { i ->
            if (i == randomCircle) {
                CreateStandardButton(
                    minSize,
                    Color(startColor),
                    changeAlpha
                ) { correctAnswer() }
            } else {
                (CreateStandardButton(
                    minSize,
                    Color(startColor)
                ) { notCorrectAnswer() })
            }
        }
    }

}

@Composable
fun TimerText(viewModel: GameViewModel, blacking: Boolean) {
    val timer = viewModel.seconds.observeAsState()

    Text(text = timer.value.toString(), style = TextStyle(blacking))
}

@Composable
fun CreateStandardButton(
    size: Dp,
    color: Color,
    newAlpha: Float = 1f,
    callback: (() -> Unit)
) {
    Button(
        onClick = { callback.invoke() },
        modifier = Modifier
            .padding(1.dp)
            .size(size)
            .alpha(newAlpha),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {}
}