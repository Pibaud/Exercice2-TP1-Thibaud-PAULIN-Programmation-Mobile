package com.example.trainapp.di

import com.example.trainapp.BuildConfig
import com.example.trainapp.data.api.AuthInterceptor
import com.example.trainapp.data.api.SncfApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.sncf.com/v1/"

    // Ajoute cet intercepteur au client OkHttp
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(BuildConfig.SNCF_API_KEY))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Affiche TOUT (URL, Corps, Headers)
        })
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val instance: SncfApiService by lazy {
        retrofit.create(SncfApiService::class.java)
    }
}