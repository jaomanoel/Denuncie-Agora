package com.example.denuncieagora.presentation.reports

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.denuncieagora.R
import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import com.example.denuncieagora.presentation.reports.components.CardReportComponent
import com.example.denuncieagora.presentation.reports.components.PillComponent
import com.example.denuncieagora.presentation.ui.Screen
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReportsScreen(
    navController: NavController,
    viewModel: ReportListViewModel = hiltViewModel()
) {
    DenuncieAgoraTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = MaterialTheme.colors.background,
                        elevation = 8.dp,
                        title = {
                            Text(
                                text = stringResource(R.string.title_page_reports),
                                style = MaterialTheme.typography.h1
                            )
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screen.NewReportScreen.route)
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add_report),
                            tint = MaterialTheme.colors.background
                        )
                    }
                },
            ) {
                val state = viewModel.state.value
                val identity = viewModel.stateIdentity.value
                val context = LocalContext.current
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

                val listFilter = mutableListOf("Todos")
                HateCrimeTypeEnum.values().forEach {
                    listFilter.add(it.name)
                }

                viewModel.getIdentity(context)

                Column(Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.custom_filter),
                        fontWeight = FontWeight.W800,
                        modifier = Modifier.padding(start = 20.dp, top = 12.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 8.dp),
                        contentPadding = PaddingValues(end = 20.dp)
                    ) {
                        val count = mutableSetOf<Int>(0)
                        items(listFilter) {
                            PillComponent(
                                label = it,
                                isEnabled = it == state.selectedReport,
                                onClick = {
                                    viewModel.getReports(if(it == "Todos") null else it)
                                }
                            )
                            count += count
                            if(count.size < listFilter.size + 1) {
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if(state.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LottieAnimation(
                                composition = composition,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            if(state.reports.isEmpty()) {
                                item {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Spacer(modifier = Modifier.height(40.dp))

                                        Icon(
                                            imageVector = Icons.Outlined.Info,
                                            contentDescription = stringResource(R.string.warning_reports_is_empty),
                                            modifier = Modifier.size(100.dp)
                                        )
                                        Text(
                                            text = stringResource(R.string.warning_reports_is_empty),
                                            fontWeight = FontWeight.W800
                                        )
                                    }
                                }
                            } else {
                                items(state.reports) { report ->
                                    CardReportComponent(
                                        id = report.id,
                                        isOwner = report.identity == identity,
                                        about =  report.about,
                                        city = report.city,
                                        state = report.state,
                                        date = report.date,
                                        description = report.description,
                                        onClickButton = {
                                            if (report.identity == identity) {
                                                viewModel.deleteReportClick(report.id, identity)
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                }

                                item {
                                    Spacer(modifier = Modifier.height(60.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}