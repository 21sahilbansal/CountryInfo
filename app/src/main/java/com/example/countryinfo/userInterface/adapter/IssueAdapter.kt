package com.example.countryinfo.userInterface.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfo.R
import com.example.countryinfo.model.CountryData

class IssueAdapter(private val items: List<CountryData>) :
    RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IssueViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.issue_row_item, parent, false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class IssueViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(countriesData : CountryData) {
            with(view) {
                txt_issue_title.text = issue.title
                txt_issue.text = issue.body
            }


        }
    }
}