package com.example.denuncieagora

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.denuncieagora.presentation.new_report.NewReportScreen
import com.example.denuncieagora.presentation.home.HomeScreen
import com.example.denuncieagora.presentation.reports.ReportsScreen
import com.example.denuncieagora.presentation.ui.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.ReportsScreen.route,
        ) {
            ReportsScreen(navController = navController)
        }
        composable(
            route = Screen.NewReportScreen.route
        ) {
            NewReportScreen(navController = navController)
        }
    }
}