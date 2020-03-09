package com.mbo.loblaws.feed

import com.mbo.loblaws.domain.Article
import com.mbo.loblaws.domain.Error

data class FeedState(
    val isLoading: Boolean,
    val error: Error?,
    val articles: List<Article>
) {
    companion object {
        val DEFAULT = FeedState(false, null, listOf())
    }
}

fun FeedState.reduce(action: FeedAction): FeedState {
    return when (action) {
        is FeedAction.UpdateFeed -> copy(isLoading = true, error = null)
        is FeedAction.FeedUpdated -> copy(articles = action.feed.articles, isLoading = false)
        is FeedAction.Error -> copy(isLoading = false, error = error)
        is FeedAction.Navigation -> this
    }
}