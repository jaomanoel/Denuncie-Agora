package com.example.denuncieagora.data.remote.dto

import com.example.denuncieagora.data.remote.ReportDto
import com.example.denuncieagora.data.remote.ReportRequestDto
import com.example.denuncieagora.data.remote.StateDto
import com.example.denuncieagora.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ReportApi {

    @GET(Constants.REPORT_ENDPOINT)
    suspend fun getReports(@Query("hatecrime") hateCrimeName: String?): List<ReportDto>

    @GET(Constants.REPORT_ENDPOINT+"/{id}")
    suspend fun getReportById(@Path("id") id: UUID): ReportDto

    @POST(Constants.REPORT_ENDPOINT)
    suspend fun createReport(@Body request: ReportRequestDto): ReportDto

    @DELETE(Constants.REPORT_ENDPOINT)
    suspend fun deleteReport(@Query("id") id: UUID, @Query("identity") identity: String): Response<Unit>
}