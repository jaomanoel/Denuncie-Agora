package com.example.denuncieagora.domain.useCases.new_report

import com.example.denuncieagora.data.remote.toCity
import com.example.denuncieagora.domain.model.City
import com.example.denuncieagora.domain.repository.IbgeRepository
import com.example.denuncieagora.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCities @Inject constructor(
    private val repository: IbgeRepository
) {

    operator fun invoke(id: Long): Flow<Resource<List<City>>> = flow {
        try {
            emit(Resource.Loading())
            val city = repository.getCities(id).map { it.toCity() }
            emit(Resource.SuccessCities(city))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Check sua conexao com a internet"))
        }
    }
}