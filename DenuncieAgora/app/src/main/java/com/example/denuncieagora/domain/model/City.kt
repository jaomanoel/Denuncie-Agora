package com.example.denuncieagora.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val nome: String
): Parcelable