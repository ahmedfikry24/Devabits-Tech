package com.example.devabitstech.data.remote.utils

import com.example.devabitstech.domain.exceptions.UnknownException
import retrofit2.Response

suspend fun <T> tryToCall(call: suspend () -> Response<T>): T {
    return try {
        val response = call()
        response.body() ?: throw UnknownException("Response body is null")
    } catch (e: Exception) {
        throw UnknownException(e.message ?: "", cause = e)
    }
}