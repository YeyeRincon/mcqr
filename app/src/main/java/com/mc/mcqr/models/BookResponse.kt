package com.mc.mcqr.models

import com.google.gson.annotations.SerializedName

data class BookResponse(

    @SerializedName("patprov")
    var patprov: Int,

    @SerializedName("cbpartnert_name")
    var cbpartnert_name: String,

    @SerializedName("ubi_nombre")
    var ubi_nombre: String,

    @SerializedName("aduser_name")
    var aduser_name: String,

    @SerializedName("ubi_glpi_id")
    var ubi_glpi_id: Int


)

