package com.example.denuncieagora.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class Constants {

    companion object {
        const val BASE_URL = "https://denuncie-agora-production.up.railway.app/";

        const val REPORT_ENDPOINT = "reports"
        const val IBGE_ENDPOINT = "ibge"

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}