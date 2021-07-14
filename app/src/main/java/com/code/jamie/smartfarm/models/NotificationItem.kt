package com.code.jamie.smartfarm.models

import android.os.Parcel
import android.os.Parcelable

data class NotificationItem(val title:String?,val description:String?,val notifyingTime:String?,val notify:Boolean?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(notifyingTime)
        parcel.writeValue(notify)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotificationItem> {
        override fun createFromParcel(parcel: Parcel): NotificationItem {
            return NotificationItem(parcel)
        }

        override fun newArray(size: Int): Array<NotificationItem?> {
            return arrayOfNulls(size)
        }
    }
}