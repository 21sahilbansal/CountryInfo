package com.example.countryinfo.userInterface.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryinfo.R
import com.example.countryinfo.base.BaseFragment
import com.example.countryinfo.callBack.FragCallBack
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.model.AllCountriesData
import com.example.countryinfo.model.CountryData
import com.example.countryinfo.userInterface.activity.MainActivity
import com.example.countryinfo.userInterface.adapter.IssueAdapter
import com.example.countryinfo.userInterface.viewModel.MainViewModel
import kotlinx.android.synthetic.main.all_country_fragment.*

class AllCountriesFragment : BaseFragment() {
    private lateinit var fragmentChangeListener: FragCallBack
    private lateinit var viewModel: MainViewModel
    private val issueData by lazy { ArrayList<CountryData>() }
    private val issueAdapter by lazy {
        IssueAdapter(issueData)
    }

    init {
        CountryInfoApplication.getInstance().appComponent.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(CountryInfoApplication.getInstance()).create(MainViewModel::class.java)
        fragmentChangeListener = context as FragCallBack

    }

    override fun viewInitialization(view: View) {
        showLoadingState(true)
        viewModel.getAllCountriesData()?.observe(this, Observer { it ->
            it.data?.let {
                setIssueData(it)
            }?: kotlin.run {
                handleError(it.throwable!!)
                showLoadingState(false)
            }
        })
    }

    private fun setIssueData(allCountriesData: AllCountriesData) {
        initAdapter()
        showLoadingState(false)
        for (countries in allCountriesData.allCountriesData){
          for(countryFlag in MainActivity.allCountryFlagData.countryFlagData){
              if(countries.alpha2Code.equals(countryFlag.code)){
                  countries.flagImage = countryFlag.unicode
              }
          }
        }
        issueData.addAll(allCountriesData.allCountriesData)
        with(issueAdapter) {
            notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        with(parent_recycler) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = issueAdapter
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.all_country_fragment
    }

    override fun showLoadingState(loading: Boolean) {
        if (loading)
            shimmer_view_container.startShimmerAnimation()
        else {
            shimmer_view_container.stopShimmerAnimation()
            shimmer_view_container.visibility = View.GONE
        }
    }

    override fun onError(message: String) {
        showToast(message)
    }



    companion object {
        val TAG = AllCountriesFragment::class.java.name
        fun newInstance(): AllCountriesFragment {
            return AllCountriesFragment()
        }
    }
}