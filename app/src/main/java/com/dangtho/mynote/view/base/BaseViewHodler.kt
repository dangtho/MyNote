package com.dangtho.mynote.view.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseViewHodler<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun binData(item: T)
}

open abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHodler<T>>() {
    protected var list: List<T>? = null
}