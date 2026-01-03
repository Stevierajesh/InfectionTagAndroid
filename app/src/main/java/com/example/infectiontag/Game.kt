package com.example.infectiontag
import  android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val id: String,
) : Parcelable
