package com.mobile.weatherforcast.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.weatherforcast.R
import com.mobile.weatherforcast.databinding.ItemDayListBinding
import com.mobile.weatherforcast.model.ForeCastDayModel
import java.text.SimpleDateFormat
import java.util.*

class DaysListAdapter(private val dataList: List<ForeCastDayModel>,val context:Context,val dayClick: DayClick) :
    RecyclerView.Adapter<DaysListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDayListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(data: ForeCastDayModel,position: Int) {
            val inFormat = SimpleDateFormat("yyyy-MM-dd")
            val date: Date = inFormat.parse(data.date)!!
            val outFormat = SimpleDateFormat("EEEE dd/MM/yyyy")
            val newDate: String = outFormat.format(date)
            newDate.also { binding.titleTextView.text = it }
            "${context.getString(R.string.condition)}${data.day.condition.text}".also { binding.descriptionTextView.text = it }
            Glide.with(context).load(data.day.condition.icon.replace("//","https://").trim()).into(binding.imageView)
            binding.dayCardView.setOnClickListener {
                dayClick.onClick(position,data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data,position)
    }

    override fun getItemCount() = dataList.size
}
