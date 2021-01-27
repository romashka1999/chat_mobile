package com.example.chat_mobile.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat_mobile.application.RetrofitBuilder
import com.example.chat_mobile.application.RetrofitBuilder.getRetrofit
import com.example.chat_mobile.payload.Group
import com.example.chat_mobile.payload.GroupCreateDto
import com.example.chat_mobile.service.GroupsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupViewModel(): ViewModel() {

    private val groupsService: GroupsService = getRetrofit().create(GroupsService::class.java)

    private val _groupsLiveData = MutableLiveData<List<Group>>()
    val groupsLiveData: LiveData<List<Group>> = _groupsLiveData

    private val _createGroupLiveData = MutableLiveData<String>()
    val createGroupLiveData: LiveData<String> = _createGroupLiveData

    fun getGroups(accessToken: String) {
        groupsService.getGroups(accessToken).enqueue(object : Callback<List<Group>> {
            override fun onResponse(call: Call<List<Group>>, response: Response<List<Group>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        _groupsLiveData.postValue(it)
                    }
                }
            }
            override fun onFailure(call: Call<List<Group>>, t: Throwable) {
                println("onFailure")
            }
        })
    }

    fun createGroup(groupCreateDto: GroupCreateDto, accessToken: String) {
        groupsService.createGroup(groupCreateDto, accessToken).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        _createGroupLiveData.postValue(it)
                    }
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("onFailure")
            }
        })
    }
}