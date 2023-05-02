package com.mobile.weatherforcast.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.weatherforcast.databinding.ItemHourListBinding
import com.mobile.weatherforcast.model.HourListModel
import java.text.SimpleDateFormat
import java.util.*

class HourListAdapter(private val dataList: List<HourListModel>, val context:Context) :
    RecyclerView.Adapter<HourListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHourListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(data: HourListModel) {
            val inFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date: Date = inFormat.parse(data.time)!!
            val outFormat = SimpleDateFormat("hh:mm a")
            val newDate: String = outFormat.format(date)
            newDate.also { binding.textViewTime.text = it }
            Glide.with(context).load(data.condition.icon.replace("//","https://").trim()).into(binding.imageView)
            "Condition :- ${data.condition.text}".also { binding.textView1.text = it }
            "Wind Gusts :- ${data.gust_kph}".also { binding.textView2.text = it }
            "Pressure :- ${data.pressure_mb}".also { binding.textView3.text = it }
            "Humidity :- ${data.humidity}%".also { binding.textView4.text = it }
            "Cloud Cover :- ${data.cloud}%".also { binding.textView5.text = it }
            "Indoor Humidity :- ${data.humidity}%".also { binding.textView6.text = it }
            "Visibility :- ${data.vis_km} km".also { binding.textView7.text = it }
            "Dew Point :- ${data.dewpoint_c}° C".also { binding.textView8.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHourListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount() = dataList.size
}
