package com.mc.mcqr.configuration

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {

    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requesBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let { authCode ->
            requesBuilder.addHeader("Authorization", "Bearer $authCode")
           // Log.i("Desde Auth: ", authCode)
           // requesBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmFzIiwiaWF0IjoxNjYwNjY3ODMzLCJleHAiOjE2NjA2NzE0MzN9.v6X58tQTbyE-ikbqPUgazxl8opUpy068X_Q929rrI_ipjnOkuDmLrBG8eXaXoDqtqiKtsvc8rTtkgAWj2fuLxQ")
            }

        return chain.proceed(requesBuilder.build())
    }
}