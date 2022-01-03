package com.dangtho.mynote.data.model

import com.google.gson.annotations.SerializedName

class N_ListUserResponse {
    @SerializedName("page")
    var page: String = ""

    @SerializedName("per_page")
    var perPage: String = ""

    @SerializedName("total")
    var total: String = ""

    @SerializedName("total_pages")
    var totalPage: String = ""

    @SerializedName("data")
    var userList: List<UserEntity>? = null
}