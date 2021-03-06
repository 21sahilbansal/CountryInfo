package com.example.countryinfo.apiService

import com.example.countryinfo.model.CountryData
import retrofit2.Call
import retrofit2.http.GET

interface AllCountryApiService {

    @GET(ApiConstants.Companion.ApiServiceConstants.ALL_COUNTRIES)
    fun getAllCountriesData(): Call<ArrayList<CountryData>>
}