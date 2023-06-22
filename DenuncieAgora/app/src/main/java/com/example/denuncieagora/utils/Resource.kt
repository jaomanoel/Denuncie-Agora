package com.example.denuncieagora.utils

import com.example.denuncieagora.data.remote.ReportDto
import com.example.denuncieagora.domain.model.City
import com.example.denuncieagora.domain.model.Report
import com.example.denuncieagora.domain.model.State

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class SuccessReports(data: List<Report>) : Resource<List<Report>>(data)
    class SuccessReport(data: ReportDto): Resource<ReportDto>(data)
    class SuccessStates(data: List<State>) : Resource<List<State>>(data)
    class SuccessCities(data: List<City>) : Resource<List<City>>(data)
    class SuccessUnit(data: Unit): Resource<Unit>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
