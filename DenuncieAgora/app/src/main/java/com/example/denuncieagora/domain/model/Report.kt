package com.example.denuncieagora.domain.model

import android.os.Parcelable
import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Report(
    val id: UUID,
    val identity: String,
    val about: HateCrimeTypeEnum,
    val date: String,
    val state: String,
    val city: String,
    val description: String
): Parcelable
