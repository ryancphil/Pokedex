package com.ryancphil.pokedex.core.data

import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified T> safeCall(execute: () -> Response<T>): Result<T> {
    val response = try {
        execute()
    } catch (e: Exception) {
        if (e is CancellationException) throw e // Coroutine pitfall.
        e.printStackTrace()
        return Result.failure(e)
    }

    return responseToResult(response)
}

inline fun <reified T> responseToResult(response: Response<T>): Result<T> {
    return when (response.code()) {
        in 200 .. 299 -> {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Exception("Error parsing response"))
        }

        else -> {
            Result.failure(Exception(response.message()))
        }
    }
}