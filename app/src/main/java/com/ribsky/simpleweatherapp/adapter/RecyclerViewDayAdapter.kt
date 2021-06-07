package com.ribsky.simpleweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ribsky.simpleweatherapp.databinding.ItemWeatherCardMainBinding
import com.ribsky.simpleweatherapp.model.day.DayModel

class RecyclerViewDayAdapter :
    RecyclerView.Adapter<RecyclerViewDayAdapter.ViewHolder>() {

    var data = listOf<DayModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder private constructor(val binding: ItemWeatherCardMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DayModel) {
            binding.dayModel = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherCardMainBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}