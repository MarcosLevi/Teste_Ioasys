package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName

data class InvestorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("investor_name")
    val investorName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("balance")
    val balance: Float,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("portfolio")
    val portfolio: PortfolioDto,
    @SerializedName("portfolio_value")
    val portfolioValue: Float,
    @SerializedName("first_access")
    val first_access: Boolean,
    @SerializedName("super_angel")
    val superAngel: Boolean
)