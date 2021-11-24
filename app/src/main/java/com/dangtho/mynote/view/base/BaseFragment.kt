package com.dangtho.mynote.view.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewModel, Binding> : Fragment() {
    protected var viewModel: ViewModel? = null
    protected var mContext: Context? = null
    protected var _binding: Binding? = null
    protected abstract fun setContext()
    protected abstract fun setViewModel()
    protected abstract fun setBinding()
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setBinding()
        setContext()
    }

    protected fun setTitleToolBar(title: String) {
        (activity as BaseActivity<*, *>).setTitleToolBar(title)
    }
}