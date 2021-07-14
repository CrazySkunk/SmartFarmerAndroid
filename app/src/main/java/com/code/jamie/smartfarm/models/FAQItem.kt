package com.code.jamie.smartfarm.models

import android.os.Parcel
import android.os.Parcelable

data class FAQItem(val title:String?):Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FAQItem> {
        override fun createFromParcel(parcel: Parcel): FAQItem {
            return FAQItem(parcel)
        }

        override fun newArray(size: Int): Array<FAQItem?> {
            return arrayOfNulls(size)
        }
    }
}