package com.ibrahim.dev.mercado_ibra.commons.network

import com.ibrahim.dev.mercado_ibra.app.utils.orString
import retrofit2.HttpException
import java.io.IOException

object HandlerResultHelper {

    suspend fun <T> getResult(request: suspend () -> T): RequestStatus<T> {
        return try {
            RequestStatus.Success(request.invoke())
        } catch (e: Throwable) {
            when (e) {
                is IOException -> RequestStatus.Error(null, "revisa tu conexion a internet")
                is HttpException -> RequestStatus.Error(e.code(), e.message().orString("error desconocido"))
                else -> RequestStatus.Error(null, "error desconocido")
            }
        }
    }
}