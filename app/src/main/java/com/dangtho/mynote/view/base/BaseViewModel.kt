package com.dangtho.mynote.view.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    protected val coroutine = CoroutineScope(Dispatchers.IO)
    protected var job: Job? = null
}