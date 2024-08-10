package com.praveenpayasi.innovaassessmenttest.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.praveenpayasi.innovaassessmenttest.R


/**
 * The AstronomyAppState class manages navigation-related state in the Astronomy app.
 * It determines whether to display a back icon in the app bar and provides
 * the title of the current screen. The rememberAstronomyAppState function
 * initializes and retains the app state across recompositions.
 * */
@Composable
fun rememberAstronomyAppState(
    navController: NavHostController,
): AstronomyState {
    return remember(navController) {
        AstronomyState(navController)
    }
}

class AstronomyState(
    private val navController: NavHostController,
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val shouldShowBackIcon: Boolean
        @Composable get() = when (currentDestination?.route) {
            Route.AstronomyScreen.name -> false
            else -> true
        }


    val showScreenTitle: String
        @Composable get() = when (navController.currentDestination?.route) {
            Route.AstronomyScreen.name -> stringResource(id = R.string.app_name)
            else -> ""
        }


}