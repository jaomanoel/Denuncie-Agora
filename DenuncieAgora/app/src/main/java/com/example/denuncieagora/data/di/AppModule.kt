package com.example.denuncieagora.data.di

import com.example.denuncieagora.data.remote.ReportRequestDto
import com.example.denuncieagora.data.remote.dto.IbgeApi
import com.example.denuncieagora.data.remote.dto.ReportApi
import com.example.denuncieagora.data.repositories.IbgeRepositoryImpl
import com.example.denuncieagora.data.repositories.ReportRepositoryImpl
import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import com.example.denuncieagora.domain.repository.IbgeRepository
import com.example.denuncieagora.domain.repository.ReportRepository
import com.example.denuncieagora.utils.Constants
import com.example.denuncieagora.utils.HateCrimeTypeEnumFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideReportApi(): ReportApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(Constants.client)
            .build()
            .create(ReportApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReportRepository(api: ReportApi): ReportRepository {
        return ReportRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideIbgeApi(): IbgeApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(Constants.client)
            .build()
            .create(IbgeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIbgeRepository(api: IbgeApi): IbgeRepository {
        return IbgeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHateCrimeTypeEnumFactory(): HateCrimeTypeEnumFactory {
        return HateCrimeTypeEnumFactory()
    }

    @Provides
    @Singleton
    fun provideIdentity(): String {
        return ""
    }

    @Provides
    @Singleton
    fun provideLocalDate(): LocalDate {
        return LocalDate.now()
    }

    @Provides
    @Singleton
    fun provideReportRequestDto(
        identity: String,
        date: LocalDate,
        state: String,
        city: String,
        description: String
    ): ReportRequestDto {
        val about = HateCrimeTypeEnum.RACISMO.ordinal
        return ReportRequestDto(identity, about, date.toString(), state, city, description)
    }
}