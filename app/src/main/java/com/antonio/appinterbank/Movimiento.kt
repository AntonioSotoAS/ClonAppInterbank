package com.antonio.appinterbank

import android.os.Parcel
import android.os.Parcelable

data class Movimiento(
    val id: String,
    val tipoMovimiento: String,
    val fecha: String,
    val tipoTransferencia: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(tipoMovimiento)
        parcel.writeString(fecha)
        parcel.writeString(tipoTransferencia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movimiento> {
        override fun createFromParcel(parcel: Parcel): Movimiento {
            return Movimiento(parcel)
        }

        override fun newArray(size: Int): Array<Movimiento?> {
            return arrayOfNulls(size)
        }
    }
}
