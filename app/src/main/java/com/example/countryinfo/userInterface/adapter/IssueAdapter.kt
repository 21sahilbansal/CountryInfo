package com.example.countryinfo.userInterface.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfo.R
import com.example.countryinfo.model.CountryData
import kotlinx.android.synthetic.main.item_country_detail.view.*

class IssueAdapter(private val countryList: List<CountryData>) :
    RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IssueViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_country_detail, parent, false
        )
    )

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    inner class IssueViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(countriesData: CountryData) {
            with(view) {
                country_name_tv.text =countriesData.name
                country_capital_tv.text = countriesData.capital
                country_currency_tv.text = countriesData?.currencies?.get(0)?.name
                try{
                Html.fromHtml(countriesData?.flagImage)?.let {it -> country_flag_image_view.text = it  }
                }
                catch (exception : Exception){

                }
            }
        }
    }
}