package com.example.denuncieagora.presentation.new_report

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.denuncieagora.R
import com.example.denuncieagora.presentation.new_report.components.CitySelectionComponent
import com.example.denuncieagora.presentation.new_report.components.DatePickerComponent
import com.example.denuncieagora.presentation.new_report.components.DropdownComponent
import com.example.denuncieagora.presentation.new_report.components.InputTextComponent
import com.example.denuncieagora.presentation.new_report.components.StateSelectionComponent
import com.example.denuncieagora.presentation.ui.Screen
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewReportScreen(
    navController: NavController,
    viewModel: NewReportViewModel = hiltViewModel()
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
                                text = stringResource(R.string.title_page_new_report),
                                style = MaterialTheme.typography.h1
                            )
                        },
                        actions = {
                            IconButton(onClick = {
                                navController.navigate(Screen.ReportsScreen.route)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.accessibility_back_home),
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            viewModel.onEvent(NewReportFormEvent.Submit)
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Create,
                            contentDescription = stringResource(R.string.button_new_report),
                            tint = MaterialTheme.colors.background
                        )
                    }
                },
            ) {
                val context = LocalContext.current
                val state = viewModel.state

                LaunchedEffect(key1 = context) {
                    viewModel.validationEvents.collect { event ->
                        when(event) {
                            is NewReportViewModel.ValidationEvent.Success -> {
                                Toast.makeText(
                                    context,
                                    R.string.success_new_report,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }

                viewModel.getIdentity(context)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 20.dp, horizontal = 18.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(R.string.label_date_report),
                        style = MaterialTheme.typography.h2
                    )
                    DatePickerComponent(
                        label = state.date,
                        error = state.dateError,
                        onSelection = {
                            viewModel.onEvent(NewReportFormEvent.DateChanged(it))
                        }
                    )

                    Text(
                        text = stringResource(R.string.label_about_report),
                        style = MaterialTheme.typography.h2
                    )
                    DropdownComponent(
                        error = state.aboutError,
                        onValueChange = { selectedType ->
                            viewModel.onEvent(NewReportFormEvent.AboutChanged(selectedType))
                        }
                    )

                    Text(
                        text = stringResource(R.string.label_state_report),
                        style = MaterialTheme.typography.h2
                    )
                    StateSelectionComponent(
                        values = state.states ?: emptyList(),
                        label = stringResource(R.string.placeholder_state_report),
                        error = state.stateError,
                        onValueChange = {
                            viewModel.onEvent(NewReportFormEvent.StateChanged(it.nome))
                            viewModel.getCitiesByState(it.id)
                        }
                    )

                    Text(
                        text = stringResource(R.string.label_city_report),
                        style = MaterialTheme.typography.h2
                    )
                    CitySelectionComponent(
                        values = state.cities ?: emptyList(),
                        label = stringResource(R.string.placeholder_city_report),
                        error = state.cityError,
                        onValueChange = {
                            viewModel.onEvent(NewReportFormEvent.CityChanged(it.nome))
                        }
                    )

                    Text(
                        text = stringResource(R.string.label_description_report),
                        style = MaterialTheme.typography.h2
                    )
                    InputTextComponent(
                        label = state.description,
                        error = state.descriptionError,
                        placeholder = stringResource(R.string.placeholder_description_report),
                        onChange = {
                            viewModel.onEvent(NewReportFormEvent.DescriptionChanged(it))
                        }
                    )

                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}