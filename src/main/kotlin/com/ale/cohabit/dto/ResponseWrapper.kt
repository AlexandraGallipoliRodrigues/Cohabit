package com.ale.cohabit.dto

data class ResponseWrapper<T>(
    val isAvailable: Boolean,
    val data: T? = null,
    val error: String? = null
)