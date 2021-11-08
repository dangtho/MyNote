package com.dangtho.mynote.view.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {
    protected val coroutine = CoroutineScope(Dispatchers.Main)
}