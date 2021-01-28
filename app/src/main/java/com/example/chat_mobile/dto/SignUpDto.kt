package com.example.chat_mobile.dto

import java.util.*

data class SignUpDto (
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
)
