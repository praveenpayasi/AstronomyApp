package com.praveenpayasi.innovaassessmenttest.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.praveenpayasi.innovaassessmenttest.ui.astronomy.AstronomyRoute

sealed class Route(val name: String) {
    object AstronomyScreen : Route("astronomy")
}

@Composable
fun AstronomyNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Route.AstronomyScreen.name
    ) {
        composable(route = Route.AstronomyScreen.name) {
            AstronomyRoute()
        }
    }

}




