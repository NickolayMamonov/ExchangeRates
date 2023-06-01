package com.whysoezzy.exchangerates.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.whysoezzy.exchangerates.R
import com.whysoezzy.exchangerates.data.model.Rates

class MainAdapter(
    private var mList: List<Rates>, private val mItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_main_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = mList[position]
        holder.currency.text = currency.name
        holder.coefficient.text = currency.value.toString()
//        holder.itemView.setOnClickListener {
//            mList[position].id.let { id -> mItemClickListener(id) }
//        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val coefficient: TextView = itemView.findViewById(R.id.coefficient)
        val currency: TextView = itemView.findViewById(R.id.currency)

    }

}