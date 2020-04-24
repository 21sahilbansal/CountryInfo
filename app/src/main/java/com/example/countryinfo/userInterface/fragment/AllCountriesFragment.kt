package com.example.countryinfo.userInterface.fragment

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryinfo.R
import com.example.countryinfo.base.BaseFragment
import com.example.countryinfo.callBack.FragCallBack
import com.example.countryinfo.common.CountryInfoApplication
import com.example.countryinfo.model.AllCountriesData
import com.example.countryinfo.model.CountryData
import com.example.countryinfo.userInterface.activity.MainActivity.Companion.allCountryFlagData
import com.example.countryinfo.userInterface.adapter.IssueAdapter
import com.example.countryinfo.userInterface.viewModel.MainViewModel
import kotlinx.android.synthetic.main.all_country_fragment.*
import kotlinx.android.synthetic.main.item_country_detail.*

class AllCountriesFragment : BaseFragment() {
    private lateinit var fragmentChangeListener: FragCallBack
    private lateinit var viewModel: MainViewModel
    private val issueData by lazy { ArrayList<CountryData>() }
    private val issueAdapter by lazy {
        IssueAdapter(issueData)
    }

    init {
        CountryInfoApplication.getAppInstance().appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(CountryInfoApplication.getAppInstance())
                .create(MainViewModel::class.java)
        fragmentChangeListener = context as FragCallBack

    }


    override fun viewInitialization(view: View) {
        showLoadingState(true)
        viewModel.getAllCountriesData()?.observe(this, Observer { it ->
            it.data?.let {

                setIssueData(it)
            } ?: kotlin.run {
                handleError(it.throwable!!)
                showLoadingState(false)
            }
        })
        initializeClickListener()
    }

    private fun initializeClickListener() {
        search_button.setOnClickListener {
            var countryFound = false
            if (country_search_etv.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter  ISOCode2 of the country ", Toast.LENGTH_LONG)
                    .show()
            } else {
                for (countries in issueData) {
                    if (countries.alpha2Code?.contentEquals(country_search_etv.text)!!) {
                        country_name_tv.text = countries.name
                        country_capital_tv.text = countries.capital
                        country_currency_tv.text = countries?.currencies?.get(0)?.name
                        country_flag_image_view.text = (Html.fromHtml(countries.flagImage))
                        country_item_container.visibility = View.VISIBLE
                        countryFound = true
                        break
                    }
                }
                if (!countryFound) {
                    Toast.makeText(
                        context,
                        "No country found ,please enter the code again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun setIssueData(allCountriesData: AllCountriesData) {
        initAdapter()
        showLoadingState(false)

        for (countries in allCountriesData.allCountriesData) {
            allCountryFlagData?.let { allCountryFlagData ->
                for (countryFlag in allCountryFlagData.CountryFlagData) {
                    if (countries.alpha2Code.equals(countryFlag.code)) {
                        countries.flagImage = countryFlag.unicode
                        break
                    }
                }
            }
        }
        issueData.addAll(allCountriesData.allCountriesData)
        with(issueAdapter) {
            notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        parent_recycler.layoutManager = linearLayoutManager
        parent_recycler.adapter = issueAdapter
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
            sugesstion_tv.visibility = View.VISIBLE
            coutry_search_ll.visibility = View.VISIBLE
            allc_tv.visibility = View.VISIBLE
            parent_recycler.visibility = View.VISIBLE
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