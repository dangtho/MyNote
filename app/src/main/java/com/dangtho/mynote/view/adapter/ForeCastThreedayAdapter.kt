package com.dangtho.mynote.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dangtho.mynote.R
import com.dangtho.mynote.data.model.ForeCast
import com.dangtho.mynote.data.util.DateUtil
import com.dangtho.mynote.data.util.StateHourCurrent
import com.dangtho.mynote.databinding.ItemHourWeatherBinding
import com.dangtho.mynote.databinding.ItemWeatherBinding
import com.dangtho.mynote.view.base.BaseAdapter
import com.dangtho.mynote.view.base.BaseViewHodler

class ForeCastThreedayAdapter : BaseAdapter<ForeCast.ForeCastDay>() {

    fun setListDay(list: List<ForeCast.ForeCastDay>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHodler<ForeCast.ForeCastDay> {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForeCastThreedayViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHodler<ForeCast.ForeCastDay>,
        position: Int
    ) {
        val item = list?.get(position) ?: return
        holder.binData(item)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}

class ForeCastThreedayViewHolder(val binding: ItemWeatherBinding) :
    BaseViewHodler<ForeCast.ForeCastDay>(binding.root) {
    override fun binData(item: ForeCast.ForeCastDay) {
        val context = binding.root.context
        binding.tvConditionItemWeather.text = String.format(
            context.resources.getString(R.string.today_format),
            DateUtil.dayString(item.date),
            item.day?.condition?.text
        )
        binding.tvTempItemWeather.text = String.format(
            context.resources.getString(R.string.tempc_temc),
            item.day?.maxTempCDay, item.day?.minTempCDay
        )
        Glide.with(context).load("https:${item.day?.condition?.icon}")
            .placeholder(R.drawable.icon_plus)
            .error(R.drawable.icon_dot_menu)
            .centerCrop()
            .into(binding.imgWeatherItemWeather)
    }
}

class ForeCastHourDayAdapter :
    BaseAdapter<ForeCast.ForeCastDay.ForeCastHour>() {
    fun setListDay(
        list: List<ForeCast.ForeCastDay.ForeCastHour>,
        listForeCastDay: List<ForeCast.ForeCastDay>
    ) {
        list.withIndex().forEach { (index, item) ->
            if (DateUtil.nearHourCurrent(item.time) == StateHourCurrent.CURRENT) {
                this.list = list.subList(index, list.size)
                return@forEach
            }
        }
        if (this.list?.size ?: 0 <= 6) {
            listForeCastDay.withIndex().forEach { (index, item) ->
                if (index == 1) {
                    item.foreCastHour?.let { this.list = this.list?.plus(it) }
                }
            }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHodler<ForeCast.ForeCastDay.ForeCastHour> {
        val binding =
            ItemHourWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForeCastHourViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHodler<ForeCast.ForeCastDay.ForeCastHour>,
        position: Int
    ) {
        val item = list?.get(position) ?: return
        holder.binData(item)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}

class ForeCastHourViewHolder(val binding: ItemHourWeatherBinding) :
    BaseViewHodler<ForeCast.ForeCastDay.ForeCastHour>(binding.root) {
    override fun binData(item: ForeCast.ForeCastDay.ForeCastHour) {
        val context = binding.root.context
        binding.tvTimeItemHour.text =
            if (DateUtil.nearHourCurrent(date = item.time) == StateHourCurrent.CURRENT) {
                "Now"
            } else {
                DateUtil.getHourString(item.time)
            }
        binding.tvTempcItemHour.text = String.format(context.getString(R.string.tempC), item.tempC)
        Glide.with(context).load("https:${item.condition?.icon}")
            .placeholder(R.drawable.icon_plus)
            .error(R.drawable.icon_dot_menu)
            .centerCrop()
            .into(binding.imgConditionItemHour)
    }
}