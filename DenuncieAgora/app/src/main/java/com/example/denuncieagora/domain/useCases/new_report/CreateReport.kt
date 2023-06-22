package com.example.denuncieagora.domain.useCases.new_report

import com.example.denuncieagora.data.remote.ReportDto
import com.example.denuncieagora.data.remote.ReportRequestDto
import com.example.denuncieagora.domain.repository.ReportRepository
import com.example.denuncieagora.utils.HateCrimeTypeEnumFactory
import com.example.denuncieagora.utils.Resource
import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class CreateReport @Inject constructor(
    private val repository: ReportRepository,
    private val request: ReportRequestDto,
    private val hateCrimeTypeEnumFactory: HateCrimeTypeEnumFactory
) {
    operator fun invoke(request: ReportRequestDto): Flow<Resource<ReportDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.createReport(request)
            emit(Resource.SuccessReport(response))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Check sua conexao com a internet"))
        }
    }

}