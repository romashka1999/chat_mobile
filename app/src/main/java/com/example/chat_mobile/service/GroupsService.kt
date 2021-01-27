package com.example.chat_mobile.service

import com.example.chat_mobile.dto.SignInDto
import com.example.chat_mobile.payload.Group
import com.example.chat_mobile.payload.GroupCreateDto
import com.example.chat_mobile.payload.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GroupsService {
    @GET("/group/getAllJoinedGroups")
    fun getGroups(@Header("Authorization") token: String): Call<List<Group>>

    @POST("/group/createGroup")
    fun createGroup(@Body groupCreateDto: GroupCreateDto, @Header("Authorization") token: String): Call<String>
}