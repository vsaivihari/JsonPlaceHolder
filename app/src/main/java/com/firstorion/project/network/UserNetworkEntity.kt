package com.firstorion.project.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserNetworkEntity(
    @SerializedName("id")
    val userId: Int,
    val name: String,
    @SerializedName("username")
    val userName: String,
    val email: String
) : Parcelable