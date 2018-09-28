package app.tez.demeter.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Terence Zafindratafa on 11/09/2018
 */
data class Recipient (var firstName: String,
                      var lastName: String,
                      var dateOfBirth: String,
                      var sexe: String,
                      var mood: Int,
                      var avatar: String?,
                      val meetingPlace: String,
                      val wasFirstMeetingPlanned: Boolean,
                      val partner: String?,
                      val firstRegistration: String,
                      val nationality: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(dateOfBirth)
        parcel.writeString(sexe)
        parcel.writeInt(mood)
        parcel.writeString(avatar)
        parcel.writeString(meetingPlace)
        parcel.writeByte(if (wasFirstMeetingPlanned) 1 else 0)
        parcel.writeString(partner)
        parcel.writeString(firstRegistration)
        parcel.writeString(nationality)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipient> {
        override fun createFromParcel(parcel: Parcel): Recipient {
            return Recipient(parcel)
        }

        override fun newArray(size: Int): Array<Recipient?> {
            return arrayOfNulls(size)
        }
    }
}