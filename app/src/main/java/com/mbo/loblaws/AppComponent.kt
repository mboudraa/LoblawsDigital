package com.mbo.loblaws

import android.content.Context
import com.mbo.loblaws.article.ArticleFragment
import com.mbo.loblaws.feed.FeedFragment
import com.mbo.loblaws.feed.FeedModule
import com.mbo.loblaws.http.HttpModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HttpModule::class,
        AppModule::class,
        FeedModule::class
    ]
)
interface AppComponent {

    fun inject(receiver: MainActivity)
    fun inject(receiver: FeedFragment)
    fun inject(receiver: ArticleFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance fun setContext(context: Context): Builder
        fun build(): AppComponent
    }
}