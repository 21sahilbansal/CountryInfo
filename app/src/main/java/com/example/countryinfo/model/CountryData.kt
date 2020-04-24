package com.example.countryinfo.model

data class CountryData(
    var alpha2Code: String?,
    var alpha3Code: String?,
    var altSpellings: List<String?>?,
    var area: Double?,
    var borders: List<String?>?,
    var callingCodes: List<String?>?,
    var capital: String?,
    var cioc: String?,
    var currencies: List<Currency?>?,
    var demonym: String?,
    var flag: String?,
    var gini: Double?,
    var languages: List<Language?>?,
    var latlng: List<Double?>?,
    var name: String?,
    var nativeName: String?,
    var numericCode: String?,
    var population: Int?,
    var region: String?,
    var regionalBlocs: List<RegionalBloc?>?,
    var subregion: String?,
    var timezones: List<String?>?,
    var topLevelDomain: List<String?>?,
    var translations: Translations?,
    var flagImage : String?
)