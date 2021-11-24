package com.dangtho.mynote.view.debug

import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.view.base.BaseViewModel

class DetailUrlResponseViewModel : BaseViewModel() {
    var urlEntityDetail = MutableLiveData<String>()
}