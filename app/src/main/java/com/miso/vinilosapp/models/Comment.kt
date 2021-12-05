package com.miso.vinilosapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val id: Int,
    val description: String,
    val rating: String
): Parcelable

