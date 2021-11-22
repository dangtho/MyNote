package com.dangtho.mynote.view.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<T> : Fragment() {
    protected var viewModel: T? = null
    protected var mContext: Context? = null
    abstract fun setContext()
    abstract fun setViewModel()
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setContext()
    }
}