package com.mbo.loblaws.feed

import com.mbo.loblaws.domain.Feed
import javax.inject.Inject

class FeedRepository @Inject constructor(private val api: FeedApi) {

    suspend fun getFeed(): Feed {
        return api.getFeed()
    }
}