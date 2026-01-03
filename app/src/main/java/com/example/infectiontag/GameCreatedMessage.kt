package com.example.infectiontag

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GameCreatedMessage(
    val type: String,
    val gameID: String
)