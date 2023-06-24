package com.example.denuncieagora.presentation.reports

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.denuncieagora.domain.useCases.reports.GetReports
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.denuncieagora.domain.useCases.reports.DeleteReport
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
): ViewModel() {
    private val _state = mutableStateOf(ReportListState())
    val state: State<ReportListState> = _state
    var stateIdentity = mutableStateOf("")

    init {
        getReports()
    }

    fun getReports(hateCrimeName: String? = null) {
        getReportsList(hateCrimeName).onEach { result ->
            when(result) {
                is Resource.SuccessReports -> {
                    _state.value = ReportListState(
                        reports = result.data ?: emptyList(),
                        selectedReport = hateCrimeName ?: "Todos"
                    )
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

    fun getIdentity(context: Context) {
        getAdvertisingInfo(context) { advertisingId ->
            stateIdentity.value = advertisingId
        }
    }
}

