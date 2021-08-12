package com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.db

import android.provider.BaseColumns


internal class DatabaseContract {
    internal class QuoteColumns : BaseColumns {
        companion object {
            const val TABLE_QUOTE = "quote"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val MAKAN = "makan"
            const val MINUM = "minum"
            const val CATEGORY = "category"
            const val DATE = "date"
        }
    }
}