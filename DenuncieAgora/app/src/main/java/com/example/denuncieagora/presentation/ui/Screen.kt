package com.example.denuncieagora.presentation.ui

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ReportsScreen : Screen("reports_screen")
    object NewReportScreen : Screen("new_report_screen")
}