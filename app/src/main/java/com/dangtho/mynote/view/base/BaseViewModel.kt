package com.dangtho.mynote.view.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {
    protected val coroutine = CoroutineScope(Dispatchers.IO)
}