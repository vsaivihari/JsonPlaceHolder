package com.firstorion.project.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PostNetworkEntity(
    @SerializedName("userId")
    @Expose
    var userId: Int,
    var postId: Int,
    var body: String,
    var title: String
) : Parcelable