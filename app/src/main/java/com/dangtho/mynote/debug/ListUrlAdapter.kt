package com.dangtho.mynote.debug

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.databinding.ItemUrlBinding
import com.dangtho.mynote.data.util.DateUtil
import com.dangtho.mynote.view.base.BaseAdapter
import com.dangtho.mynote.view.base.BaseViewHodler

class ListUrlAdapter : BaseAdapter<UrlEntity>() {
     var listener: Listener? = null
    fun setListUrl(list: List<UrlEntity>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHodler<UrlEntity> {
        val binding = ItemUrlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listUrlViewHodle(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHodler<UrlEntity>, position: Int) {

        list?.get(position)?.let {
            holder.binData(it)
        }
        holder as listUrlViewHodle
        holder.binding.root.setOnClickListener {
            listener?.onClick(list?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}
interface Listener {
    fun onClick(item: UrlEntity?)
}
class listUrlViewHodle(val binding: ItemUrlBinding) : BaseViewHodler<UrlEntity>(binding.root) {
    override fun binData(item: UrlEntity) {
        binding.tvLinkUrl.text = item.urlLink
        binding.tvDateTime.text = DateUtil.getStringFromLong(item.dateTime)
    }
}