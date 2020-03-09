package com.mbo.loblaws

import com.mbo.loblaws.http.BaseUrl
import com.mbo.redux.SideEffect
import com.mbo.redux.Store
import com.mbo.redux.sideEffect
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import timber.log.Timber
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @JvmStatic
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideAppStore(
            sideEffects: Set<@JvmSuppressWildcards SideEffect<AppState>>
    ): Store<AppState> {
        return Store(
                initialState = AppState.DEFAULT,
                reducer = { state, action -> state.reduce(action) },
                sideEffects = sideEffects,
                scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        )
    }

    @Provides
    @JvmStatic
    @IntoSet
    fun provideLogSideEffect(): SideEffect<AppState> {
        return sideEffect { _, action ->
            Timber.tag("** Store [New Action]").d(action::class.java.name)
        }
    }

    @Provides
    @JvmStatic
    @IntoSet
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply { setLevel(BODY) }
    }

}