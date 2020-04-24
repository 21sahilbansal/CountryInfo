package com.example.countryinfo.apiService

import com.example.countryinfo.model.AllCountriesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AllCountryApiService {

    @GET(ApiConstants.Companion.ApiServiceConstants.ALL_COUNTRIES)
    fun getAllCountriesData(): Call<AllCountriesData>
}