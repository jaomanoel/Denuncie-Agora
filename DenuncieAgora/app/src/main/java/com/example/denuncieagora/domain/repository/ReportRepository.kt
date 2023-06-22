package com.example.denuncieagora.domain.repository

import com.example.denuncieagora.data.remote.ReportDto
import com.example.denuncieagora.data.remote.ReportRequestDto
import com.example.denuncieagora.data.remote.StateDto
import retrofit2.Response
import java.util.UUID

interface ReportRepository {

    suspend fun getReports(hateCrimeName: String?): List<ReportDto>

    suspend fun getReportById(id: UUID): ReportDto

    suspend fun createReport(request: ReportRequestDto): ReportDto

    suspend fun deleteReport(id: UUID, identity: String): Response<Unit>
}