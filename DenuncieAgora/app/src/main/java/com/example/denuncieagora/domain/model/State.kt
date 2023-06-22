package com.example.denuncieagora.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val id: Long,
    val nome: String
): Parcelable