package com.dangtho.mynote.view.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment<ViewModel, Binding> : Fragment() {
    protected var viewModel: ViewModel? = null
    protected var mContext: Context? = null
    protected var _binding: Binding? = null
    protected abstract fun setContext()
    protected abstract fun setViewModel()
    protected abstract fun setBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setBinding()
        setContext()
    }

    protected fun setTitleToolBar(title: String) {
        (activity as BaseActivity<*, *>).setTitleToolBar(title)
    }

    private fun onBackPressedCallback(): OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }

        }
    }
}