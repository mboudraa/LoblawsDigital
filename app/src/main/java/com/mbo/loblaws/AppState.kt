package com.mbo.loblaws

import com.mbo.loblaws.article.ArticleState
import com.mbo.loblaws.feed.FeedAction
import com.mbo.loblaws.feed.FeedState
import com.mbo.loblaws.feed.reduce
import com.mbo.redux.Action

data class AppState(
    val feedState: FeedState,
    val articleState: ArticleState?
) {
    companion object {
        val DEFAULT = AppState(
            feedState = FeedState.DEFAULT,
            articleState = null
        )
    }
}

fun AppState.reduce(action: Action): AppState {
    return when (action) {
        is FeedAction -> when (action) {
            is FeedAction.Navigation.ArticleDetail -> copy(
                articleState = ArticleState(action.article),
                feedState = feedState.reduce(action)
            )
            else -> copy(feedState = feedState.reduce(action))
        }
        else -> this
    }
}

