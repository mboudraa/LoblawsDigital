package com.mbo.loblaws.feed

import androidx.navigation.NavController
import com.mbo.loblaws.domain.Article
import com.mbo.loblaws.domain.Feed
import com.mbo.loblaws.domain.toError
import com.mbo.loblaws.feed.FeedFragmentDirections.Companion.actionFeedToArticle
import com.mbo.redux.Action
import com.mbo.redux.NavigationAction
import com.mbo.redux.SideEffect
import javax.inject.Inject

sealed class FeedAction : Action {
    object UpdateFeed : FeedAction()
    data class FeedUpdated(val feed: Feed) : FeedAction()
    data class Error(val error: com.mbo.loblaws.domain.Error) : FeedAction()
    sealed class Navigation : FeedAction(),
        NavigationAction {
        data class ArticleDetail(val article: Article) : Navigation()
    }
}

fun NavController.navigate(action: FeedAction.Navigation) {
    when (action) {
        is FeedAction.Navigation.ArticleDetail -> {
            navigate(actionFeedToArticle(action.article.title))
        }
    }
}

class UpdateFeedSideEffect @Inject constructor(
    private val repository: FeedRepository
) : SideEffect<FeedState> {
    override suspend fun invoke(state: FeedState, action: Action): Action? {
        if (action !is FeedAction.UpdateFeed) return null
        return try {
            val feed = repository.getFeed()
            FeedAction.FeedUpdated(feed)
        } catch (e: Exception) {
            FeedAction.Error(e.toError())
        }
    }
}