package com.zx.kotlin_android.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zx.kotlin_android.R
import com.zx.kotlin_android.domain.model.Forecast

import com.zx.kotlin_android.domain.model.ForecastList
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.find
import kotlin.math.max

class RecyclerAdapter(val weekForecast: ForecastList,val itemClick: (Forecast) -> Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context)
                .inflate(R.layout.item_forecast,p0,false)


        return ViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return weekForecast.size()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView.text = items[p1]
      p0.bindForecast(weekForecast[p1])
    }
    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view){
//        private val iconView: ImageView
//        private val dateView: TextView
//        private val descriptionView: TextView
//        private val maxTemperatureView: TextView
//        private val minTemperatureView: TextView
//        init {
//            iconView = view.find(R.id.icon)
//            dateView = view.find(R.id.date)
//            descriptionView = view.find(R.id.description)
//            maxTemperatureView = view.find(R.id.maxTemperature)
//            minTemperatureView = view.find(R.id.minTemperature)
//        }
        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.context).load(icon).into(itemView.icon)
                itemView.date.text = date.toString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }
    }
    /**
     * 使用lambda代替
     */
//    interface OnItemClickListener{
//        operator fun invoke(forecast: Forecast)
//    }

}
