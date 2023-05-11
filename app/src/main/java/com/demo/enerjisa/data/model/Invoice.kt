package com.demo.enerjisa.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Invoice(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("documentNumber")
    val documentNumber: String,
    @SerializedName("dueDate")
    val dueDate: String,
    @SerializedName("installationNumber")
    val installationNumber: String,
    @SerializedName("legacyContractAccountNumber")
    val legacyContractAccountNumber: String
):Parcelable