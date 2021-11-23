package com.dangtho.mynote.view.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toolbar
import com.dangtho.mynote.databinding.MyToolBarBinding

class MyToolBar : Toolbar {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private var navigationLeftListener: () -> Unit = {}
    private var navigationRightListener: () -> Unit = {}
    private var _binding: MyToolBarBinding? = null
    private lateinit var binding: MyToolBarBinding
    fun init(context: Context) {
        _binding = MyToolBarBinding.inflate(LayoutInflater.from(context), this, true)
        binding = _binding!!
        setContentInsetsRelative(0, 0)
        binding.imgAddPlace.setOnClickListener { navigationLeftListener.invoke() }
        binding.imgMenu.setOnClickListener { navigationRightListener.invoke() }
    }

    fun setTitleToolBar(title: String?) {
        binding.tvTitlePlace.text = title
    }

    fun setUpNavigationRightListener(navigationRightListener: () -> Unit) {
        this.navigationRightListener = navigationRightListener
    }

    fun setUpNavigationLeftListener(navigationLeftListener: () -> Unit) {
        this.navigationLeftListener = navigationLeftListener
    }
}