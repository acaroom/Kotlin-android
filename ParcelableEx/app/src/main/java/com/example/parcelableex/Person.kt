package com.example.parcelableex

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(val name: String?, val age: Int): Parcelable