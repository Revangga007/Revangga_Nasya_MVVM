package com.revangga.revangga_nasya_mvvm.data.model


import com.google.gson.annotations.SerializedName

class DogsResponse : ArrayList<DogsData>()

data class DogsData(
    @SerializedName("breeds")
    val breed: List<Any>,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int

)