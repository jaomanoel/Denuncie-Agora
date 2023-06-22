package com.example.denuncieagora.data.remote

import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import com.example.denuncieagora.domain.model.Report
import java.time.LocalDate
import java.util.UUID

data class ReportDto(
    val id: UUID,
    val identity: String,
    val about: HateCrimeTypeEnum,
    val date: String,
    val state: String,
    val city: String,
    val description: String
)

fun ReportDto.toReport(): Report {
    return Report(
        id = id,
        identity = identity,
        about = about,
        date = date,
        state = state,
        city = city,
        description = description
    )
}