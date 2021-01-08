package com.example.chat_mobile.service

import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.dto.SignUpDto
import retrofit2.Call
import retrofit2.http.POST

interface AuthService {
    @POST("/signup")
    fun signUp(signUpDto: SignUpDto): Call<String>
    @POST("/signin")
    fun signIn(signInDto: SignInDto): Call<String>
}