package com.demo.enerjisa.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    @SerializedName("address")
    val address: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("contractAccountNumber")
    val contractAccountNumber: String,
    @SerializedName("installationNumber")
    val installationNumber: String
): Parcelable