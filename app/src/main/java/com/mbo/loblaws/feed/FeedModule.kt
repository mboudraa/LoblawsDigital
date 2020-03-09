package com.mbo.loblaws.feed

import com.mbo.loblaws.AppState
import com.mbo.loblaws.http.JsonAdapter
import com.mbo.redux.SideEffect
import com.mbo.redux.actionSideEffect
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import retrofit2.Retrofit
import retrofit2.create

@Module
object FeedModule {
    @Provides @JvmStatic @ElementsIntoSet @JsonAdapter
    fun provideFeedJsonAdapters(): Set<Any> {
        return setOf(
            FeedJsonAdapter(),
            ArticleJsonAdapter()
        )
    }

    @Provides @Reusable @JvmStatic
    fun provideChallengeApi(retrofit: Retrofit): FeedApi {
        return retrofit.create()
    }

    @Provides @JvmStatic @IntoSet
    fun provideSideEffect(sideEffect: UpdateFeedSideEffect): SideEffect<AppState> {
        return actionSideEffect { state, action -> sideEffect(state.feedState, action) }
    }
}