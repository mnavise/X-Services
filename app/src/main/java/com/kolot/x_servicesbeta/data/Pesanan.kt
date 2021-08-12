package com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.data

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Pesanan(
    var id: String? = null,
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var date: Timestamp? = null
) : Parcelable