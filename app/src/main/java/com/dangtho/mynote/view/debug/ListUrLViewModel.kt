package com.dangtho.mynote.view.debug

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.Repository.MainDataBaseRepository
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListUrLViewModel @Inject constructor(
    private val mainDataBaseRepository: MainDataBaseRepository
) : BaseViewModel() {

    fun getAllUrl(): LiveData<List<UrlEntity>> {
        return mainDataBaseRepository.getAll()
    }
}