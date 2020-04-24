package com.example.countryinfo.userInterface.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.countryinfo.R
import com.example.countryinfo.base.BaseActivity
import com.example.countryinfo.callBack.FragCallBack
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.model.countriesFlagData.AllCountryFlagData
import com.example.countryinfo.userInterface.fragment.AllCountriesFragment
import com.google.gson.Gson

class MainActivity : BaseActivity(), FragCallBack {
    lateinit var  allCountryFlagData : AllCountryFlagData
    init {
        CountryInfoApplication.getInstance().appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openAllCountriesFragment()
        getVehiclesFlagData()
    }
    private fun getVehiclesFlagData() {
        val jsonString = resources.openRawResource()
            .bufferedReader().use { it.readText() }
        convertToGsonObjects(jsonString)
    }
    private fun convertToGsonObjects(jsonString: String) {
        var gson = Gson()
        allCountryFlagData = gson.fromJson(jsonString, AllCountryFlagData::class.java)
    }
    override fun getLayoutRes() = R.layout.activity_main


    private fun openAllCountriesFragment() {
        replaceFragment(
            R.id.container,
            AllCountriesFragment.newInstance(),
            AllCountriesFragment.TAG
        )
    }


    override fun onFragmentChange(fragment: Fragment, tag: String) {
        replaceFragment(R.id.container, fragment, tag, true)
    }
}
