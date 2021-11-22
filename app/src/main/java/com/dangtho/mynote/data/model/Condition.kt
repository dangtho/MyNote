package com.dangtho.mynote.data.model

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

    override fun toString(): String {
        return "Condition(text='$text', code='$code', icon='$icon')"
    }
}