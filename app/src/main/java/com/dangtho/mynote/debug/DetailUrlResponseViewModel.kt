package com.dangtho.mynote.debug

import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.view.base.BaseViewModel

class DetailUrlResponseViewModel : BaseViewModel() {
    var urlEntityDetail = MutableLiveData<String>()
}