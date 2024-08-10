package com.praveenpayasi.innovaassessmenttest.data.repository

import com.praveenpayasi.innovaassessmenttest.data.api.NetworkService
import com.praveenpayasi.innovaassessmenttest.data.local.LocalStorageService
import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AstronomyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val localStorageService: LocalStorageService) {

    fun getAstronomyFromRemote(): Flow<Astronomy> {
        return flow {
            emit(networkService.getAstronomy())
        }
    }

    fun getAstronomyFromPref(): Flow<Astronomy> {
        return localStorageService.getAstronomy(Astronomy::class.java)
    }

    suspend fun setAstronomyIntoPref(astronomy: Astronomy) {
        return localStorageService.saveAstronomy(astronomy)
    }



}