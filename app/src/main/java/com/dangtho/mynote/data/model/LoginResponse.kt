package com.dangtho.mynote.data.model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("token")
    var token: String = ""

    @SerializedName("error")
    var error: String = ""
}