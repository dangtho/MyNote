package com.dangtho.mynote.view.base

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dangtho.mynote.debug.ListUrlActivity
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<ViewModel, Binding> : AppCompatActivity() {
    protected var viewModel: ViewModel? = null
    protected var _binding: Binding? = null
    abstract fun setViewModel()
    abstract fun setBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
    }
}