package com.example.countryinfo.userInterface.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.countryinfo.R
import com.example.countryinfo.base.BaseActivity
import com.example.countryinfo.callBack.FragCallBack
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.userInterface.fragment.AllCountriesFragment

class MainActivity : BaseActivity() , FragCallBack {
init {
    CountryInfoApplication.getInstance().appComponent.inject(this)

}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openIssueFragment()
    }

    override fun getLayoutRes() = R.layout.activity_main


    private fun openIssueFragment() {
        replaceFragment(R.id.container, AllCountriesFragment, AllCountriesFragment.TAG)
    }


    override fun onFragmentChange(fragment: Fragment, tag: String) {
        replaceFragment(R.id.container, fragment, tag, true)
    }
}
