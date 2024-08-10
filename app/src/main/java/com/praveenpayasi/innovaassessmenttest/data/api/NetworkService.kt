package com.praveenpayasi.innovaassessmenttest.data.api

import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy
import com.praveenpayasi.innovaassessmenttest.utils.AppConstant.API_KEY
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("planetary/apod?api_key=$API_KEY")
    suspend fun getAstronomy(): Astronomy

}