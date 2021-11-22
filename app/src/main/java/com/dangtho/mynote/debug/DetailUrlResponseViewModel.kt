package com.dangtho.mynote.debug

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.view.base.BaseViewModel

class DetailUrlResponseViewModel(context: Context): BaseViewModel() {
    var urlEntityDetail = MutableLiveData<String>()
}