package com.praveenpayasi.innovaassessmenttest.ui.astronomy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveenpayasi.innovaassessmenttest.base.UiState
import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy
import com.praveenpayasi.innovaassessmenttest.data.repository.AstronomyRepository
import com.praveenpayasi.innovaassessmenttest.utils.NetworkHelper
import com.praveenpayasi.innovaassessmenttest.utils.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyViewModel @Inject constructor(
    private val astronomyRepository: AstronomyRepository,
    private val networkHelper: NetworkHelper,
    private val logger: Logger
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Astronomy>>(UiState.Loading)

    val uiState: StateFlow<UiState<Astronomy>> = _uiState

    private fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    init {
        startFetchingAstronomy()
    }

    fun startFetchingAstronomy() {
        if (checkInternetConnection()) {
            fetchAstronomy()
        } else {
            fetchAstronomyFromPref()
        }
    }

    private fun fetchAstronomyFromPref() {
        viewModelScope.launch(Dispatchers.Main) {
            astronomyRepository.getAstronomyFromPref()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    if (!checkInternetConnection() && it.toString().isEmpty()) {
                        _uiState.value = UiState.Error("Data Not found.")
                    } else {
                        _uiState.value = UiState.Success(it)
                        logger.d("Offline", "Success")
                    }
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun fetchAstronomy() {
        viewModelScope.launch(Dispatchers.Main) {
            astronomyRepository.getAstronomyFromRemote()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.flatMapConcat {
                    flow {
                        emit(
                            astronomyRepository.setAstronomyIntoPref(
                                it
                            )
                        )
                    }
                }.flowOn(Dispatchers.IO).catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    fetchAstronomyFromPref()
                }
        }
    }

}