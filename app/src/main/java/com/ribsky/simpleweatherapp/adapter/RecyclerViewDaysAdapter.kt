package com.ribsky.simpleweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ribsky.simpleweatherapp.databinding.ItemWeatherCardInfoBinding
import com.ribsky.simpleweatherapp.model.days.DaysModel

class RecyclerViewDaysAdapter :
    RecyclerView.Adapter<RecyclerViewDaysAdapter.ViewHolder>() {

    var data = listOf<DaysModel>()
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

    class ViewHolder private constructor(val binding: ItemWeatherCardInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DaysModel) {
            binding.daysModel = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherCardInfoBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}