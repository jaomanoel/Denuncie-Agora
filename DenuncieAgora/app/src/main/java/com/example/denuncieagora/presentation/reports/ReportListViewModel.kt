package com.example.denuncieagora.presentation.reports

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.denuncieagora.domain.useCases.reports.GetReports
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.denuncieagora.domain.useCases.reports.DeleteReport
import com.example.denuncieagora.domain.useCases.reports.GetReportById
import com.example.denuncieagora.utils.Resource
import com.example.denuncieagora.utils.getAdvertisingInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ReportListViewModel @Inject constructor(
    private val getReportsList: GetReports,
    private val deleteReport: DeleteReport,
    private val getReportById: GetReportById
): ViewModel() {
    private val _state = mutableStateOf(ReportListState())
    val state: State<ReportListState> = _state
    var stateIdentity = mutableStateOf("")

    init {
        getReports()
    }

    fun getReports(hateCrimeName: String? = null) {
        if(hateCrimeName != null) {
            _state.value = ReportListState(selectedReport = hateCrimeName)
        }
        getReportsList(hateCrimeName).onEach { result ->
            when(result) {
                is Resource.SuccessReports -> {
                    _state.value = ReportListState(reports = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ReportListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = ReportListState(isLoading = true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun deleteReportClick(id: UUID, identity: String) {
        deleteReport(id, identity).onEach { result ->
            when (result) {
                is Resource.SuccessUnit -> {
                    getReports()
                }
                is Resource.Loading -> {
                    _state.value = ReportListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Error -> {
                    _state.value = ReportListState(error = result.message ?: "An unexpected error occured")
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

   /* fun getReportId(id: UUID) {

        getReportById(id).onEach { result ->
            when(result) {
                is Resource.SuccessReport -> {
                    if(result.data?.identity != stateIdentity.value) {
                        getReports()
                    }

                    if(result.data?.identity == stateIdentity.value) {
                        deleteReportClick(result.data.id, stateIdentity.value)
                    }
                }
                is Resource.Error -> {
                    _state.value = ReportListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = ReportListState(isLoading = true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }*/

    fun getIdentity(context: Context): Unit {
        getAdvertisingInfo(context) { advertisingId ->
            stateIdentity.value = advertisingId
        }
    }
}

