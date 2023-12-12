package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LogInRequest(
    val userName: String,
    val phoneNumber: String,
    val profilePhoto: String? = null
)