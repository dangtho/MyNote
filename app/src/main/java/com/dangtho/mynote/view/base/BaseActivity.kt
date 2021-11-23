package com.dangtho.mynote.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<ViewModel, Binding> : AppCompatActivity() {
    protected var viewModel: ViewModel? = null
    protected var _binding: Binding? = null
    abstract fun setViewModel()
    abstract fun setBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setViewModel()
    }
}