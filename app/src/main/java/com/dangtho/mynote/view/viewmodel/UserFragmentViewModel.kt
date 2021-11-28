package com.dangtho.mynote.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.Repository.MainDataBaseRepository
import com.dangtho.mynote.data.api.NetworkHelper
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.N_ListUserResponse
import com.dangtho.mynote.data.model.PersonInfoResponse
import com.dangtho.mynote.data.model.base.Result
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFragmentViewModel @Inject constructor(
    private val mainApiRepository: MainApiRepository,
    private val mainDataBaseRepository: MainDataBaseRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {
    private var _result = MutableStateFlow(Result<N_ListUserResponse>())
    val result: StateFlow<Result<N_ListUserResponse>> = _result
    private var listUser = object : MutableLiveData<List<PersonInfoResponse>>() {}

    init {
        job = coroutine.launch {
            getListUsersFromAPi()
        }
    }

    private suspend fun getListUsersFromAPi() {
        if (networkHelper.isNetworkConnected()) {
            mainApiRepository.getListUser().let {
                if (it.isSuccessful) {
                    _result.value = Result.success(it.body())
                    mainDataBaseRepository.insert(it.body()?.userList ?: emptyList())
                } else {
                    _result.value = Result.error(it.errorBody()?.string() ?: "", null)
                }
            }
        } else {
            _result.value = Result.error("No internet", null)
        }
    }

    fun getListUserFromDataBase(): LiveData<List<PersonInfoResponse>> {
        return mainDataBaseRepository.getAllUser()
    }

    fun updateUser(user: PersonInfoResponse) {
        mainDataBaseRepository.insert(user)
    }

    fun getTokenUser(id: String): LiveData<PersonInfoResponse> {
        return mainDataBaseRepository.getToken(id)
    }
}