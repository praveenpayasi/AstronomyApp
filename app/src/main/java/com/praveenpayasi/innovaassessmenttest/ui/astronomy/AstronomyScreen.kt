package com.praveenpayasi.innovaassessmenttest.ui.astronomy

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.praveenpayasi.innovaassessmenttest.base.Astronomy
import com.praveenpayasi.innovaassessmenttest.base.ShowError
import com.praveenpayasi.innovaassessmenttest.base.ShowLoading
import com.praveenpayasi.innovaassessmenttest.base.UiState
import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy

@Composable
fun AstronomyRoute(
    viewModel: AstronomyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        val show = viewModel.checkForDataWithErrorMsg()
        Log.d("show Astronomy ","$show")
        AstronomyScreen(uiState, show, onRetryClick = {
            viewModel.startFetchingAstronomy()
        })
    }

}


@Composable
fun AstronomyScreen(uiState: UiState<Astronomy>, show : Boolean, onRetryClick: () -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            Astronomy(uiState.data,show)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message) {
                onRetryClick()
            }
        }
    }
}