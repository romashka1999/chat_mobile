package com.example.chat_mobile.application

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private lateinit var instance: Retrofit

    fun getInstance(accessToken: String): Retrofit {
        if(instance.equals(null)) {
            instance = build(accessToken)
        }
        return instance
    }

     companion object {
         fun build(accessToken: String): Retrofit {
             val BASE_URL = "http://10.0.2.2:8080"
             val okHttpClientBuilder = OkHttpClient.Builder()
             okHttpClientBuilder
                 .addInterceptor { chain ->
                     val request: Request = chain.request()
                     val newRequest: Request.Builder =
                         request.newBuilder().header("Authorization", accessToken)
                     chain.proceed(newRequest.build())
                 }

             return Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .client(okHttpClientBuilder.build())
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
         }
     }


}