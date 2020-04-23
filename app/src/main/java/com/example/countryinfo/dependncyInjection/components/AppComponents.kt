package com.example.countryinfo.dependncyInjection.components

import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.dependncyInjection.modules.ApiModule
import com.example.countryinfo.repository.CountriesApiServiceProvider
import com.example.countryinfo.userInterface.activity.MainActivity
import com.example.countryinfo.userInterface.fragment.AllCountriesFragment
import com.example.countryinfo.userInterface.viewModel.MainViewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule :: class])
interface AppComponents {

    fun inject (mainViewModel: MainViewModel)
    fun inject (countriesApiServiceProvider: CountriesApiServiceProvider)
    fun inject (allCountriesFragment: AllCountriesFragment)
    fun inject (fireBaseGitHubApplication: CountryInfoApplication)
    fun inject (mainActivity: MainActivity)
}