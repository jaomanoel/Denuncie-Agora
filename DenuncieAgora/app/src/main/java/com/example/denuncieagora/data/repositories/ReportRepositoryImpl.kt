package com.example.denuncieagora.data.repositories

import com.example.denuncieagora.data.remote.ReportDto
import com.example.denuncieagora.data.remote.ReportRequestDto
import com.example.denuncieagora.data.remote.dto.ReportApi
import com.example.denuncieagora.domain.repository.ReportRepository
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val api: ReportApi
) : ReportRepository {

    override suspend fun getReports(hateCrimeName: String?): List<ReportDto> {
        return api.getReports(hateCrimeName)
    }

    override suspend fun getReportById(id: UUID): ReportDto {
        return api.getReportById(id)
    }

    override suspend fun createReport(request: ReportRequestDto): ReportDto {
        return api.createReport(request)
    }

    override suspend fun deleteReport(id: UUID, identity: String): Response<Unit> {
        return api.deleteReport(id, identity)
    }

}