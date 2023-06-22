package com.example.denuncieagora.utils

import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import javax.inject.Singleton

@Singleton
class HateCrimeTypeEnumFactory {
    private var currentHateCrimeType: HateCrimeTypeEnum = HateCrimeTypeEnum.RACISMO

    fun setCurrentHateCrimeType(hateCrimeType: HateCrimeTypeEnum) {
        currentHateCrimeType = hateCrimeType
    }

    fun provideHateCrimeTypeEnum(): HateCrimeTypeEnum {
        return currentHateCrimeType
    }
}