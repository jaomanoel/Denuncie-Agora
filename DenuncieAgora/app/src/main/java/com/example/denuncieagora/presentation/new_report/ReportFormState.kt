package com.example.denuncieagora.presentation.new_report

import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import com.example.denuncieagora.domain.model.City
import com.example.denuncieagora.domain.model.State
import java.time.LocalDate

data class ReportFormState(
    val identity: String? = null,
    val about: HateCrimeTypeEnum? = null,
    val aboutError: String? = null,
    val date: LocalDate? = null,
    val dateError: String? = null,
    val state: String = "",
    val states: List<State>? = null,
    val stateError: String? = null,
    val city: String = "",
    val cities: List<City>? = null,
    val cityError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
)
