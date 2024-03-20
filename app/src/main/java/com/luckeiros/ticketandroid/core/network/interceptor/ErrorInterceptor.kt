package com.luckeiros.ticketandroid.core.network.interceptor

import android.util.Log
import com.luckeiros.ticketandroid.core.network.BusinessException
import com.luckeiros.ticketandroid.core.network.TimeoutException
import com.luckeiros.ticketandroid.core.network.UnknownException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = try {
            chain.proceed(request)
        } catch (e: SocketTimeoutException) {
            throw TimeoutException(e)
        } catch (e: Exception) {
            throw UnknownException(e)
        }

        if (!response.isSuccessful) {
            val responseBody = response.peekBody(Long.MAX_VALUE).string()
            Log.e("API_ERROR", createLogTemplate(request, response, responseBody))
            throw BusinessException(IOException("Response failed. Code: ${response.code()}"))
        }

        return response
    }

    private fun createLogTemplate(request: Request, response: Response, body: String): String {
        return """
            |---- API Error ----
            |Endpoint: ${request.url()}
            |Method: ${request.method()}
            |Status Code: ${response.code()}
            |Response Body: $body
            |-------------------
        """.trimMargin()
    }
}