package com.mbo.loblaws.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit

inline fun okHttpClient(f: OkHttpClient.Builder.() -> Unit = {}): OkHttpClient {
    return OkHttpClient.Builder().apply(f).build()
}

inline fun retrofit(f: Retrofit.Builder.() -> Unit = {}): Retrofit {
    return Retrofit.Builder().apply(f).build()
}