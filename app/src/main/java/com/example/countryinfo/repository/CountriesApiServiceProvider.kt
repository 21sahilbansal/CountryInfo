package com.example.countryinfo.repository

import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.apiService.AllCountryApiService
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.helper.WrapperDataClass
import com.example.countryinfo.model.AllCountriesData
import com.example.countryinfo.model.CountryData
import com.example.countryinfo.network.RetrofitCallBack
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class CountriesApiServiceProvider @Inject constructor(var allCountryApiService: AllCountryApiService) {

    init {
        CountryInfoApplication.getAppInstance().appComponent.inject(this)
    }

    var mutableLiveData: MutableLiveData<WrapperDataClass<AllCountriesData>> = MutableLiveData()
    var allCountryDataWrapper = WrapperDataClass<AllCountriesData>()

    fun getAllCountiresList(): MutableLiveData<WrapperDataClass<AllCountriesData>> {
        allCountryApiService.getAllCountriesData()
            .enqueue(object : RetrofitCallBack<ArrayList<CountryData>>() {
                override fun handleSuccess(
                    call: Call<ArrayList<CountryData>>?,
                    response: Response<ArrayList<CountryData>>?
                ) {
                    var issuesDataResponse = response?.body()
                    allCountryDataWrapper.data = issuesDataResponse?.let { AllCountriesData(it) }
                    mutableLiveData.postValue(allCountryDataWrapper)
                }

                override fun handleFailure(call: Call<ArrayList<CountryData>>?, t: Throwable?) {
                    allCountryDataWrapper.throwable = t
                    mutableLiveData.postValue(allCountryDataWrapper)
                }
            })
        return mutableLiveData
    }

}


