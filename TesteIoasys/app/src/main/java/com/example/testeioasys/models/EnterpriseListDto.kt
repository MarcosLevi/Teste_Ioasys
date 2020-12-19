package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName

data class EnterpriseListDto(
    @SerializedName("enterprises")
    val enterprises: MutableList<EnterpriseDto>
)