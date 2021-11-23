package com.dangtho.mynote.debug

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.database.AppDataBase
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListUrLViewModel @Inject constructor(
    private val dataBase: AppDataBase
) : BaseViewModel() {

    fun getAllUrl(): LiveData<List<UrlEntity>> {
        return dataBase.urlLinkDao().getAll()
    }
}