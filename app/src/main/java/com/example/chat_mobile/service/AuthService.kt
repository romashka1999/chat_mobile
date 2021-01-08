package com.example.chat_mobile.service

import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.dto.SignUpDto
import com.example.chat_mobile.payload.SignInResponse
import com.example.chat_mobile.payload.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/signup")
    fun signUp(@Body signUpDto: SignUpDto): Call<SignUpResponse>
    @POST("/signin")
    fun signIn(@Body signInDto: SignInDto): Call<SignInResponse>
}