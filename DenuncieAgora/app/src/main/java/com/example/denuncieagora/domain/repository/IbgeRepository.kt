package com.example.denuncieagora.domain.repository

import com.example.denuncieagora.data.remote.CityDto
import com.example.denuncieagora.data.remote.StateDto

interface IbgeRepository {

    suspend fun getStates(): List<StateDto>

    suspend fun getCities(id: Long): List<CityDto>
}