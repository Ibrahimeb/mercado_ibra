package com.ibrahim.dev.mercado_ibra.commons.network

import android.util.Log
import com.ibrahim.dev.mercado_ibra.commons.utils.orAlternative
import retrofit2.HttpException
import java.io.IOException

object HandlerResultHelper {

    suspend fun <T> getResult(request: suspend () -> T): RequestStatus<T> {
        return try {
            RequestStatus.Success(request.invoke())
        } catch (e: Throwable) {
            when (e) {
                is IOException -> RequestStatus.Error(null, "revisa tu conexion a internet")
                is HttpException -> {
                    Log.e("HandlerResultHelper", "getResult: ${e.message}")
                    RequestStatus.Error(
                        e.code(),
                        e.message().orAlternative("HttpException -> ${e.code()}")
                    )
                }
                else -> {
                    Log.e("HandlerResultHelper", "getResult: ${e.message}")
                    RequestStatus.Error(null, e.message.orAlternative("error desconocido"))
                }
            }
        }
    }
}