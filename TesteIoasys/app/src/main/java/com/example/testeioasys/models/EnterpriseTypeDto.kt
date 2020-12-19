package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName

data class EnterpriseTypeDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("enterprise_type_name")
    val enterpriseTypeName: String
)