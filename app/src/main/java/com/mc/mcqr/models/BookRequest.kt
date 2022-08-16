package com.mc.mcqr.models

import com.google.gson.annotations.SerializedName

data class BookRequest(
    @SerializedName("ad_user_id")
    var ad_user_id: String,

    @SerializedName("token")
    var token: String
)
