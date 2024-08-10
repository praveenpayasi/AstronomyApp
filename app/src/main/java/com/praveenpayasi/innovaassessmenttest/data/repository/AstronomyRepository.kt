package com.praveenpayasi.innovaassessmenttest.data.repository

import com.praveenpayasi.innovaassessmenttest.data.api.NetworkService
import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AstronomyRepository @Inject constructor(private val networkService: NetworkService) {

    fun getAstronomy(): Flow<Astronomy> {
        return flow {
            emit(networkService.getAstronomy())
        }
    }

}