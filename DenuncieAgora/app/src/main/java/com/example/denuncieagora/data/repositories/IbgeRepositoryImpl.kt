package com.example.denuncieagora.data.repositories

import com.example.denuncieagora.data.remote.CityDto
import com.example.denuncieagora.data.remote.StateDto
import com.example.denuncieagora.data.remote.dto.IbgeApi
import com.example.denuncieagora.domain.repository.IbgeRepository
import javax.inject.Inject

class IbgeRepositoryImpl @Inject constructor(
    private val api: IbgeApi
): IbgeRepository {

    override suspend fun getStates(): List<StateDto> {
        return api.getStates()
    }

    override suspend fun getCities(id: Long): List<CityDto> {
        return api.getCities(id);
    }
}