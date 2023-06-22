package com.example.denuncieagora.domain.useCases.reports

import com.example.denuncieagora.data.remote.ReportDto
import com.example.denuncieagora.domain.repository.ReportRepository
import com.example.denuncieagora.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

class GetReportById @Inject constructor(
    private val repository: ReportRepository
) {

    operator fun invoke(id: UUID): Flow<Resource<ReportDto>> = flow {
        try {
            emit(Resource.Loading())
            val report = repository.getReportById(id)
            emit(Resource.SuccessReport(report))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Check sua conexao com a internet"))
        }
    }
}