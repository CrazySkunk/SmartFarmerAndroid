package com.code.jamie.smartfarm.models

import android.os.Parcel
import android.os.Parcelable

data class PagerItem (val title:String?,val imageUrl:Int?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        if (imageUrl != null) {
            parcel.writeInt(imageUrl)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PagerItem> {
        override fun createFromParcel(parcel: Parcel): PagerItem {
            return PagerItem(parcel)
        }

        override fun newArray(size: Int): Array<PagerItem?> {
            return arrayOfNulls(size)
        }
    }
}