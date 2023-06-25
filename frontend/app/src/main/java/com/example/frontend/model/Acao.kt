package com.example.frontend.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Acao(
    val id: Int,
    val nome: String,
    val tipo: String,
    val descricao: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nome)
        parcel.writeString(tipo)
        parcel.writeString(descricao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Acao> {
        override fun createFromParcel(parcel: Parcel): Acao {
            return Acao(parcel)
        }

        override fun newArray(size: Int): Array<Acao?> {
            return arrayOfNulls(size)
        }
    }
}

