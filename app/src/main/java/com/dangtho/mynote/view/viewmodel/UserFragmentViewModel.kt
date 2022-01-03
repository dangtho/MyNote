package com.dangtho.mynote.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.Repository.MainDataBaseRepository
import com.dangtho.mynote.data.api.NetworkHelper
import com.dangtho.mynote.data.model.N_ListUserResponse
import com.dangtho.mynote.data.model.PersonEntity
import com.dangtho.mynote.data.model.UserEntity
import com.dangtho.mynote.data.model.base.Result
import com.dangtho.mynote.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
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
    var listUser: MutableStateFlow<List<UserEntity>> = MutableStateFlow(emptyList())
    var listPerson: LiveData<List<PersonEntity>>? = object : LiveData<List<PersonEntity>>() {}


    init {
        job = coroutine.launch {
            getAllPerson()
            getListUsersFromAPi()
            Log.e("Vvvvvvvv", "" + listPerson?.value?.size)
            listPerson?.value?.forEach {
                Log.e("Vvvvvvvv", "" + it.firstName)
            }
            getListUserFromData()
        }
        job?.onJoin
    }

    fun cancelJob() {
        job?.cancelChildren()
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

//    fun getListUserFromDataBase(): LiveData<List<UserEntity>> {
//        return mainDataBaseRepository.getAllUser()
//    }

    fun getListUserFromData() {
        listUser.value = mainDataBaseRepository.getAllUser()
        Log.e("Vvvvvvvv", "listUser" + listUser.value?.size)
    }

    fun updateUser(user: UserEntity) {
        mainDataBaseRepository.insert(user)
    }

    fun getTokenUser(id: String): LiveData<UserEntity> {
        return mainDataBaseRepository.getToken(id)
    }

    private suspend fun getAllPerson() {
        listPerson = mainDataBaseRepository.getAllPerson()
    }

    fun getItem(position: Int): StateFlow<UserEntity> {
        val result = MutableStateFlow(UserEntity())
        result.value = listUser.value[position]
        return result
    }
}