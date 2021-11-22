package com.dangtho.mynote.debug

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.data.database.AppDataBase
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUrLViewModel @Inject constructor(
    private val dataBase: AppDataBase
) : BaseViewModel() {
    private var _allUrl = MutableLiveData<List<UrlEntity>>()
    val allUrl: LiveData<List<UrlEntity>> = _allUrl

    init {
        getAllUrl()
    }

    private fun getAllUrl() {
        coroutine.launch {
            _allUrl.postValue(dataBase.urlLinkDao().getAll().value)
        }
    }
}