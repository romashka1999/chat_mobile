package com.example.chat_mobile.view_model

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat_mobile.R
import com.example.chat_mobile.application.RetrofitBuilder
import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.dto.SignUpDto
import com.example.chat_mobile.payload.SignInResponse
import com.example.chat_mobile.payload.SignUpResponse
import com.example.chat_mobile.service.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(activity : Activity): ViewModel() {

    private val accessToken = "roma"

    private val authService: AuthService = RetrofitBuilder().getInstance(accessToken!!).create(AuthService::class.java)

    private val _signUpLiveData = MutableLiveData<SignUpResponse>()
    val signUpLiveData: LiveData<SignUpResponse> = _signUpLiveData

    private val _signInLiveData = MutableLiveData<SignInResponse>()
    val signInLiveData: LiveData<SignInResponse> = _signInLiveData


    fun signUp(signUpDto: SignUpDto) {
        authService.signUp(signUpDto).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        _signUpLiveData.postValue(it)
                    }
                }
            }
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                println("onFailure")
            }
        })
    }

    fun signIn(signInDto: SignInDto) {
        authService.signIn(signInDto).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _signInLiveData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                println("onFailure")
            }
        })
    }

}