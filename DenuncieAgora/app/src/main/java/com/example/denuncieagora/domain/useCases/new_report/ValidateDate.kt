package com.example.denuncieagora.domain.useCases.new_report

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class ValidateDate {

    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(date: LocalDate?): ValidationResult {
        if(date == null) {
            return ValidationResult(successful = false,
                errorMessage = "Data é necessária."
            )
        }

        if (date > LocalDate.now()){
            return ValidationResult(successful = false,
                errorMessage = "Data e invalida."
            )
        }

        return ValidationResult(successful = true, errorMessage = null)
    }
}