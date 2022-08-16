package com.mc.mcqr.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("token")
    var token: String,

    @SerializedName("id")
    var id: Int
)
