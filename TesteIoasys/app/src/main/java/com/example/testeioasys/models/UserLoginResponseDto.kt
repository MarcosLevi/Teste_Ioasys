package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName

data class UserLoginResponseDto(
    @SerializedName("investor")
    val investor: InvestorDto? = null,
    @SerializedName("enterprise")
    val enterprise: EnterpriseDto? = null,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("errors")
    val errors: List<String> = mutableListOf(),
    var acessToken: String?,
    var client: String?,
    var uid: String?
)