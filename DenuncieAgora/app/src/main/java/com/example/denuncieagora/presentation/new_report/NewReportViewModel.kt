package com.example.denuncieagora.presentation.new_report

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.denuncieagora.data.remote.ReportRequestDto
import com.example.denuncieagora.domain.model.State
import com.example.denuncieagora.domain.useCases.new_report.CreateReport
import com.example.denuncieagora.domain.useCases.new_report.GetCities
import com.example.denuncieagora.domain.useCases.new_report.GetStates
import com.example.denuncieagora.domain.useCases.new_report.ValidateAbout
import com.example.denuncieagora.domain.useCases.new_report.ValidateCity
import com.example.denuncieagora.domain.useCases.new_report.ValidateDate
import com.example.denuncieagora.domain.useCases.new_report.ValidateDescription
import com.example.denuncieagora.domain.useCases.new_report.ValidateState
import com.example.denuncieagora.utils.getAdvertisingInfo
import com.example.denuncieagora.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewReportViewModel @Inject constructor(
    private val createReport: CreateReport,
    private val getStates: GetStates,
    private val getCities: GetCities,
): ViewModel() {
    private val validateAbout: ValidateAbout
    private val validateCity: ValidateCity
    private val validateDescription: ValidateDescription
    private val validateState: ValidateState
    private val validateDate: ValidateDate

    var state by mutableStateOf(ReportFormState())

    init {
        validateDate = ValidateDate()
        validateCity = ValidateCity()
        validateState = ValidateState()
        validateAbout = ValidateAbout()
        validateDescription = ValidateDescription()

        getAllStates()
    }

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: NewReportFormEvent){
        when(event){
            is NewReportFormEvent.AboutChanged -> {
                state = state.copy(about = event.about)
            }
            is NewReportFormEvent.CityChanged -> {
                state = state.copy(city = event.city)
            }
            is NewReportFormEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }
            is NewReportFormEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
            is NewReportFormEvent.StateChanged -> {
                state = state.copy(state = event.state)
            }
            is NewReportFormEvent.Submit -> {
                submitData()
            }
        }
    }

    fun getIdentity(context:  Context): Unit {
        getAdvertisingInfo(context) { advertisingId ->
            state = state.copy(identity = advertisingId)
        }
    }

    fun getAllStates(): Unit {
        getStates().onEach { result ->
            when(result) {
                is Resource.SuccessStates -> {
                    state = state.copy(states = result.data ?: emptyList())
                }
                is Resource.Error -> {
//                    state = state.copy(error = result.message ?: "An unexpected error occured")
                    println("loading...")
                }
                is Resource.Loading -> {
//                    _state.value = ReportListState(isLoading = true)
                    println("error...")
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getCitiesByState(id: Long): Unit {
        getCities(id).onEach { result ->
            when(result) {
                is Resource.SuccessCities -> {
                    state = state.copy(cities = result.data ?: emptyList())
                }
                is Resource.Error -> {
//                    state = state.copy(error = result.message ?: "An unexpected error occured")
                    println("loading...")
                }
                is Resource.Loading -> {
//                    _state.value = ReportListState(isLoading = true)
                    println("error...")
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun submitData() {
        val dateResult = validateDate.invoke(state.date)
        val cityResult = validateCity.invoke(state.city)
        val stateResult = validateState.invoke(state.state)
        val descriptionResult = validateDescription.invoke(state.description)
        val aboutResult = validateAbout.invoke(state.about)

        val hasError = listOf(
            dateResult,
            cityResult,
            stateResult,
            descriptionResult,
            aboutResult
        ).any {!it.successful }

        if (hasError) {
            state = state.copy(
                aboutError = aboutResult.errorMessage,
                cityError = cityResult.errorMessage,
                dateError = dateResult.errorMessage,
                stateError = stateResult.errorMessage,
                descriptionError = descriptionResult.errorMessage,
            )
            return
        } else {
            state = state.copy(
                aboutError = null,
                cityError = null,
                dateError = null,
                stateError = null,
                descriptionError = null,
            )
        }

        val request = ReportRequestDto(
            identity = state.identity!!,
            state = state.state,
            description = state.description,
            date = state.date!!.toString(),
            city = state.city,
            about = state.about!!.ordinal + 1
        )

        sendRequisition(request)
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private fun sendRequisition(request: ReportRequestDto) {
        createReport(request).onEach { result ->
            when(result) {
                is Resource.SuccessReport -> {
                    println("success")
                }
                is Resource.Loading -> {
                    println("loading...")
                }
                is Resource.Error -> {
                    println(result.message)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}

