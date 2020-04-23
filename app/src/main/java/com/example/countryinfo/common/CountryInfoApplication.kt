package com.example.countryinfo.common

import android.app.Application
import com.example.countryinfo.dependncyInjection.components.AppComponents
import com.example.countryinfo.dependncyInjection.modules.ApiModule

class CountryInfoApplication : Application() {

    lateinit var  appComponent : AppComponents


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponents.builder().apiModule(ApiModule(this)).build()
        appComponent.inject(this)
    }

    companion object{
        lateinit var instance : CountryInfoApplication
        fun getInstance() :CountryInfoApplication{
            return instance
        }
    }


}