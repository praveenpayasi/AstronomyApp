package com.praveenpayasi.innovaassessmenttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.praveenpayasi.innovaassessmenttest.base.AstronomyNavHost
import com.praveenpayasi.innovaassessmenttest.base.GlobalTopBar
import com.praveenpayasi.innovaassessmenttest.base.rememberAstronomyAppState
import com.praveenpayasi.innovaassessmenttest.ui.theme.InnovaAssessmentTestTheme
import com.praveenpayasi.innovaassessmenttest.ui.theme.gray40
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val appState = rememberAstronomyAppState(navController)

            InnovaAssessmentTestTheme {
                Scaffold(
                    topBar = {
                        GlobalTopBar(
                            title = appState.showScreenTitle,
                            shouldShowBackIcon = appState.shouldShowBackIcon,
                            onBackClick = navController::popBackStack
                        )
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .background(gray40),
                    ) {
                        AstronomyNavHost(navController)
                    }
                }

            }
        }

    }
}