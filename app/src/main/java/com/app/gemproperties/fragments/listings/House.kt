package com.app.gemproperties.fragments.listings

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class House(
    var complete: Boolean,
    val id: Int,
    val title: String
): Parcelable