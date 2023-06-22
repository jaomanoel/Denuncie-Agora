package com.example.denuncieagora.domain.useCases.new_report

import com.example.denuncieagora.data.remote.toState
import com.example.denuncieagora.domain.model.State
import com.example.denuncieagora.domain.repository.IbgeRepository
import com.example.denuncieagora.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStates @Inject constructor(
    private val repository: IbgeRepository
) {

    operator fun invoke(): Flow<Resource<List<State>>> = flow {
        try {
            emit(Resource.Loading())
            val states = repository.getStates().map { it.toState() }
            emit(Resource.SuccessStates(states))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Check sua conexao com a internet"))
        }
    }
}