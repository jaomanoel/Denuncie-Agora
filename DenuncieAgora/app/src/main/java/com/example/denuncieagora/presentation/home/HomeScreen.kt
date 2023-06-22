package com.example.denuncieagora.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.denuncieagora.R
import com.example.denuncieagora.presentation.ui.Screen
import com.example.denuncieagora.presentation.ui.components.ButtonComponent
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

@Composable
fun HomeScreen(navController: NavController) {
    val develop = remember {
        mutableStateOf("")
    }

    DenuncieAgoraTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column (
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp),
                    painter = painterResource(R.drawable.bg_home),
                    contentDescription = null
                )

                ButtonComponent(
                    label = stringResource(R.string.button_home),
                    onClick = {
                        navController.navigate(Screen.ReportsScreen.route)
                    },
                    modifier = Modifier.width(120.dp)
                )
            }
            val developName: String = stringResource(id = R.string.developer)

            LaunchedEffect(Unit) {
                developName.forEach { char ->
                    if (develop.value.length < developName.length) {
                        develop.value += char.toString()
                        delay(100)
                    }
                }
            }

            Box {
                Text(
                    text = develop.value,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp)
                )
            }
        }
    }
}