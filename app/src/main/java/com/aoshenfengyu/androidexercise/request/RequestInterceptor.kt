package com.aoshenfengyu.androidexercise.request

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

import java.io.IOException

class RequestInterceptor(private val mContext: Context?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (mContext == null) {
            throw NullPointerException("Please call RequestManager.getInstance().init(context) first")
        }
        val request = chain.request()
        val builder = request.newBuilder()
        val urlBuilder = request.url.newBuilder()
        val httpUrl = urlBuilder.build()
        val newRequest = builder.url(httpUrl).build()
        Log.i("RequestInterceptor", "\n" + newRequest.toString() + "\n")
        try {
            return chain.proceed(newRequest)
        } catch (e: Exception) {
            Log.e(TAG, "intercept Exception:$e")
            return chain.proceed(request)
        }

    }

    companion object {
        val TAG = "RequestInterceptor"
    }
}
