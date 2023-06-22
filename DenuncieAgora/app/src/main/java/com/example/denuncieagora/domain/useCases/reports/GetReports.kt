package com.example.denuncieagora.domain.useCases.reports

import com.example.denuncieagora.data.remote.toReport
import com.example.denuncieagora.domain.model.Report
import com.example.denuncieagora.domain.repository.ReportRepository
import com.example.denuncieagora.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReports @Inject constructor(
    private val repository: ReportRepository
) {

    operator fun  invoke(hateCrimeName: String?): Flow<Resource<List<Report>>> = flow {
        try {
            emit(Resource.Loading())
            val reports = repository.getReports(hateCrimeName).map { it.toReport() }
            emit(Resource.SuccessReports(reports))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Check sua conexao com a internet"))
        }
    }
}