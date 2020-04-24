package com.example.countryinfo.userInterface.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.helper.WrapperDataClass
import com.example.countryinfo.model.AllCountriesData
import com.example.countryinfo.repository.CountriesApiServiceProvider
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var apiServiceProvider: CountriesApiServiceProvider

    init {
        CountryInfoApplication.getInstance().appComponent.inject(this)
    }


    fun getAllCountriesData(): MutableLiveData<WrapperDataClass<AllCountriesData>>? {
        return apiServiceProvider?.let { it.getAllCountiresList() }
    }
}