package com.example.countryinfo.repository

import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.apiService.AllCountryApiService
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.helper.WrapperDataClass
import com.example.countryinfo.model.AllCountriesData
import com.example.countryinfo.network.RetrofitCallBack
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class CountriesApiServiceProvider @Inject constructor(var allCountryApiService: AllCountryApiService) {

    init {
        CountryInfoApplication.getInstance().appComponent.inject(this)
    }

    var mutableLiveData: MutableLiveData<WrapperDataClass<AllCountriesData>> = MutableLiveData()


    var allCountryDataWrapper = WrapperDataClass<AllCountriesData>()

    fun getFireBaseIosIssues(): MutableLiveData<WrapperDataClass<AllCountriesData>> {
        allCountryApiService.getAllCountriesData()
            .enqueue(object : RetrofitCallBack<AllCountriesData>() {
                override fun handleSuccess(
                    call: Call<AllCountriesData>?,
                    response: Response<AllCountriesData>?
                ) {
                    var issuesDataResponse = response?.body()
                    allCountryDataWrapper.data = issuesDataResponse
                    mutableLiveData.postValue(allCountryDataWrapper)
                }

                override fun handleFailure(call: Call<AllCountriesData>?, t: Throwable?) {
                    allCountryDataWrapper.throwable = t
                    mutableLiveData.postValue(allCountryDataWrapper)
                }
            })
        return mutableLiveData
    }


}


