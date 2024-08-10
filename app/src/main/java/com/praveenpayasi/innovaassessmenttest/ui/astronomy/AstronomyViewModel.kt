package com.praveenpayasi.innovaassessmenttest.ui.astronomy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveenpayasi.innovaassessmenttest.base.UiState
import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy
import com.praveenpayasi.innovaassessmenttest.data.repository.AstronomyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyViewModel @Inject constructor(private val astronomyRepository: AstronomyRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Astronomy>>(UiState.Loading)

    val uiState: StateFlow<UiState<Astronomy>> = _uiState

    init {
        fetchAstronomy()
    }

    private fun fetchAstronomy() {
        viewModelScope.launch {
            astronomyRepository.getAstronomy()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}