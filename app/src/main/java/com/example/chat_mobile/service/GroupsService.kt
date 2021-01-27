package com.example.chat_mobile.service

import com.example.chat_mobile.payload.Group
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GroupsService {
    @GET("/group/getAllJoinedGroups")
    fun getGroups(): Call<List<Group>>
}