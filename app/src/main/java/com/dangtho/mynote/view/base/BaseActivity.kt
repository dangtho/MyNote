package com.dangtho.mynote.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dangtho.mynote.view.customview.MyToolBar

abstract class BaseActivity<ViewModel, Binding> : AppCompatActivity() {
    protected var viewModel: ViewModel? = null
    protected var _binding: Binding? = null
    protected var myToolBar: MyToolBar? = null
    protected abstract fun setViewModel()
    protected abstract fun setBinding()
    protected abstract fun setMyToolBar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setBinding()
        setViewModel()
    }

    fun setTitleToolBar(title: String) {
        myToolBar?.setTitleToolBar(title)
    }
}