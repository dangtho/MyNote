package com.dangtho.mynote.data.model

import com.google.gson.annotations.SerializedName

class N_PersonInfoResponse {
    @SerializedName("id")
    var id: String =""

    @SerializedName("email")
    var email: String =""

    @SerializedName("first_name")
    var firstName: String =""

    @SerializedName("last_name")
    var lastName: String =""

    @SerializedName("avatar")
    var avatar: String =""
}