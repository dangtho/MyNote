package com.dangtho.webview.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    val _isVisible = MutableLiveData<Int>()
    val isVisible: LiveData<Int> = _isVisible
    val isDetail = MutableLiveData<Boolean>()
}