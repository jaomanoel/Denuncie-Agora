package com.example.denuncieagora.domain.useCases.new_report

import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum

class ValidateAbout {

    operator fun invoke(about: HateCrimeTypeEnum?): ValidationResult {
        if(about == null) {
            return ValidationResult(successful = false,
                errorMessage = "Sobre é necessário."
            )
        }

        if(!HateCrimeTypeEnum.values().contains(about)) {
            return ValidationResult(
                successful = false,
                errorMessage = "O tipo do crime nao e valido."
            )
        }

        return ValidationResult(successful = true, errorMessage = null)
    }
}