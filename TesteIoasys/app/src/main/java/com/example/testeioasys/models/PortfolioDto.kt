package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName

data class PortfolioDto(
    @SerializedName("enterprises_number")
    val enterprisesNumber: Int,
    @SerializedName("enterprises")
    val enterprises: List<EnterpriseDto>
)