package com.code.jamie.smartfarm.models

import android.os.Parcel
import android.os.Parcelable

data class ShopItem(val title:String?,val location:String?,val description:String?,val imageUrl:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<ShopItem> {
        override fun createFromParcel(parcel: Parcel): ShopItem {
            return ShopItem(parcel)
        }

        override fun newArray(size: Int): Array<ShopItem?> {
            return arrayOfNulls(size)
        }
    }
}