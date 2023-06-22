package com.example.denuncieagora.domain.useCases.reports

import com.example.denuncieagora.domain.repository.ReportRepository
import com.example.denuncieagora.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class DeleteReport @Inject constructor(
    private val repository: ReportRepository
){

    operator fun invoke(id: UUID, identity: String): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.deleteReport(id, identity)
            if (response.isSuccessful) {
                emit(Resource.SuccessUnit(Unit))
            } else {
                emit(Resource.Error("An unexpected error occurred"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Check sua conexao com a internet"))
        }
    }
}