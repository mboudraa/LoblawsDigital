package com.mbo.loblaws.http

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object HttpModule {

    @Provides @Reusable @JvmStatic
    fun provideOkHttpClient(
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ) = okHttpClient {
        interceptors.forEach { addInterceptor(it) }
    }

    @Provides @JvmStatic @Reusable
    fun provideRetrofit(
        httpClient: OkHttpClient,
        @BaseUrl baseUrl: String,
        moshi: Moshi
    ) = retrofit {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides @Reusable @JvmStatic
    fun provideMoshi(
        @JsonAdapter adapters: Set<@JvmSuppressWildcards Any> = setOf()
    ): Moshi {
        return Moshi.Builder()
            .apply { adapters.forEach { add(it) } }
            .build()
    }


}
