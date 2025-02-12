package com.example.chat_mobile.application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    init {
        build()
    }

    private const val BASE_URL = "http://10.0.2.2:3000"
    private lateinit var INSTANCE: Retrofit

    private fun build() {
        INSTANCE = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofit(): Retrofit {
        return INSTANCE
    }
}