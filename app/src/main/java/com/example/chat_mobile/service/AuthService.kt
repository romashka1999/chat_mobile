package com.example.chat_mobile.service

import androidx.annotation.RequiresApi
import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.dto.SignUpDto
import com.example.chat_mobile.payload.SignInResponse
import com.example.chat_mobile.payload.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/user/signUp")
    fun signUp(@Body signUpDto: SignUpDto): Call<SignUpResponse>

    @POST("/auth/user/signIn")
    fun signIn(@Body signInDto: SignInDto): Call<SignInResponse>
}