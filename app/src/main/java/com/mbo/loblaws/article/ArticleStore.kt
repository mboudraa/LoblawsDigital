package com.mbo.loblaws.article

import com.mbo.loblaws.AppState
import com.mbo.redux.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class ArticleStore @Inject constructor(
    private val store: Store<AppState>
) {

    fun observeState(): Flow<ArticleState> {
        return store.observeState().mapNotNull { state -> state.articleState }
    }

}
