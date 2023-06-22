package com.example.denuncieagora.presentation.new_report

import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import java.time.LocalDate

sealed class NewReportFormEvent {
    data class AboutChanged(val about: HateCrimeTypeEnum): NewReportFormEvent()
    data class CityChanged(val city: String): NewReportFormEvent()
    data class StateChanged(val state: String): NewReportFormEvent()
    data class DescriptionChanged(val description: String): NewReportFormEvent()
    data class DateChanged(val date: LocalDate): NewReportFormEvent()

    object Submit: NewReportFormEvent()
}
