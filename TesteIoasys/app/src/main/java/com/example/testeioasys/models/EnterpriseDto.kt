package com.example.testeioasys.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EnterpriseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("enterprise_name")
    val enterpriseName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email_enterprise")
    val emailEnterprise: String,
    @SerializedName("facebook")
    val facebook: String,
    @SerializedName("twitter")
    val twitter: String,
    @SerializedName("linkedin")
    val linkedin: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("own_enterprise")
    val ownEnterprise: Boolean,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("value")
    val value: Int,
    @SerializedName("shares")
    val shares: Int,
    @SerializedName("share_price")
    val sharePrice: Float,
    @SerializedName("own_shares")
    val ownShares: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("enterprise_type")
    val enterpriseType: EnterpriseTypeDto
) : Serializable