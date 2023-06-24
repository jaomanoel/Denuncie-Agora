package com.example.denuncieagora.presentation.reports

import com.example.denuncieagora.domain.model.Report

data class ReportListState(
    val isLoading: Boolean = false,
    val reports: List<Report> = emptyList(),
    val error: String = "",
    val selectedReport: String = "Todos"
)
