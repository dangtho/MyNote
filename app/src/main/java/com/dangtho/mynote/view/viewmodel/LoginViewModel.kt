package com.dangtho.mynote.view.viewmodel

import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.api.NetworkHelper
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.base.Result
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainApiRepository: MainApiRepository,
    private val networkHelper: NetworkHelper
) :
    BaseViewModel() {
    companion object {
        const val EMAIL = "eve.holt@reqres.in"
        const val PASSWORD = "cityslicka"
    }

    var email = MutableStateFlow("")

    var password = MutableStateFlow("")
    private var _result = MutableStateFlow(Result<LoginResponse>())
    val result: StateFlow<Result<LoginResponse>> = _result

    fun doLogin() {
        job = coroutine.launch {
            if (networkHelper.isNetworkConnected()) {
                mainApiRepository.login(email = email.value, password = password.value)
                    .let { response ->
                        if (response.isSuccessful) {
                            _result.value = Result.success(response.body())
                        } else {
                            _result.value =
                                Result.error("email or password wrong", response.body())
                        }
                    }
            } else {
                _result.value = Result.error("No internet", null)
            }
        }
    }

    fun cancelDoLogin() {
        job ?: return
        job?.cancel()
        job = null
    }
}