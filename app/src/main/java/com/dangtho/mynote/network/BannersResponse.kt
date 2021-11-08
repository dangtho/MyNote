package com.dangtho.mynote.network

import com.google.gson.annotations.SerializedName

class BannersResponse {
    @SerializedName("id")
    var id: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("content")
    var content: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("image_url")
    var imageUrl: String = ""

    @SerializedName("thumbnail_url")
    var thumbnailUrl: String = ""

    @SerializedName("mobile_url")
    var mobileUrl: String = ""

    @SerializedName("ratio")
    var ratio: String = ""
}