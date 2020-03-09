package com.mbo.loblaws.feed

import com.mbo.loblaws.domain.Feed
import retrofit2.http.GET

interface FeedApi {
    @GET("r/Kotlin/.json")
    suspend fun getFeed(): Feed


}