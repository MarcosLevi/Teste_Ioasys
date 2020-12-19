package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName

data class EnterpriseByIdResponseDto(
    @SerializedName("enterprise")
    val enterprise: EnterpriseDto,
    @SerializedName("success")
    val success: Boolean
)