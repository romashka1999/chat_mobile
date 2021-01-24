package com.example.chat_mobile.service

import com.example.chat_mobile.payload.Group
import retrofit2.Call
import retrofit2.http.GET

interface GroupsService {
    @GET("/groups/signup")
    fun getGroups(): Call<List<Group>>
}