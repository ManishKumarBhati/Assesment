package com.manishbhati.data.network


import com.manishbhati.data.util.AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeaderInterceptor @Inject internal constructor(val sessionManager: SessionManager) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request().newBuilder()
        sessionManager.fetchAuthToken()?.let {
            original.header(AUTHORIZATION, it)
        }
        return chain.proceed(original.build())
    }
}