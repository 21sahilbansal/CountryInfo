package com.example.countryinfo.apiService

class ApiConstants {
    companion object {
        const val BASE_URL = "https://restcountries.eu/rest/v2/"
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"

        object ApiServiceConstants {
            const val ALL_COUNTRIES = "all"
        }
    }

}