package com.dangtho.mynote.model

import com.google.gson.annotations.SerializedName

class Condition {
    @SerializedName("text")
    var text: String = ""

    @SerializedName("code")
    var code: String = ""

    @SerializedName("icon")
    var icon: String = ""

    constructor(text: String, code: String, icon: String) {
        this.text = text
        this.code = code
        this.icon = icon
    }
}