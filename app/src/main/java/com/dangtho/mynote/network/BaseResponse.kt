package com.dangtho.mynote.network

import com.google.gson.annotations.SerializedName

/**
 * base reponse of api
 */
class BaseResponse<T> {
    @SerializedName("data")
    var data: List<T>? = null
}

/**
 * response of api login
 */
class LoginResponse {
    @SerializedName("status")
    var status: Int = 0

    @SerializedName("id")
    var id: String = ""

    @SerializedName("token")
    var token: String = ""
}