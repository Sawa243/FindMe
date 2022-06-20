package com.sawaProduct.findme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sawaProduct.findme.R
import com.sawaProduct.findme.TextStyle
import com.sawaProduct.findme.storages.SettingsStorage
import com.sawaProduct.findme.ui.theme.Cyan
import com.sawaProduct.findme.ui.theme.Pink80

@Composable
fun RecordScreen(
    navController: NavController,
    stateBackground: Color,
    modifier: Modifier
) {
    val records by remember {
        mutableStateOf(SettingsStorage.listRecords.list)
    }
    val blacking by remember {
        mutableStateOf(stateBackground == Color.Black)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(stateBackground)
    ) {
        Column(
            modifier = modifier
                .padding(top = 100.dp, bottom = 60.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(id = R.string.top_records),
                style = TextStyle(blacking)
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = modifier.padding(top = 30.dp, bottom = 50.dp)
            ) {
                items(records.size) { index ->
                    Text(
                        text = records[index].toString(),
                        style = TextStyle(blacking),
                        fontSize = 25.sp,
                        modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
                    )
                }
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