package com.dangtho.mynote.view.viewmodel

import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.api.NetworkHelper
import com.dangtho.mynote.data.model.N_ListUserResponse
import com.dangtho.mynote.data.model.base.Result
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFragmentViewModel @Inject constructor(
    private val mainApiRepository: MainApiRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {
    private var _result = MutableStateFlow(Result<N_ListUserResponse>())
    val result: StateFlow<Result<N_ListUserResponse>> = _result

     fun getListUsers() {
        job = coroutine.launch {
            if (networkHelper.isNetworkConnected()) {
                mainApiRepository.getListUser().let {
                    if (it.isSuccessful) {
                        _result.value = Result.success(it.body())
                    } else {
                        _result.value = Result.error(it.errorBody()?.string() ?: "", null)
                    }
                }
            } else {
                _result.value = Result.error("No internet", null)
            }
        }
    }
}