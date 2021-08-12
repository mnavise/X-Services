package com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quote(
        var id: Int = 0,
        var title: String? = null,
        var description: String? = null,
        var makan: String? = null,
        var minum: String? = null,
        var category: String? = null,
        var date: String? = null
) : Parcelable