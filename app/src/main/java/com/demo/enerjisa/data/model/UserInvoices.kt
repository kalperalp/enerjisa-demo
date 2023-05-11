package com.demo.enerjisa.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInvoices(
    @SerializedName("invoices")
    val invoices: List<Invoice>,
    @SerializedName("list")
    val list: List<Company>,
    @SerializedName("totalPrice")
    val totalPrice: String,
    @SerializedName("totalPriceCount")
    val totalPriceCount: Int
): Parcelable
