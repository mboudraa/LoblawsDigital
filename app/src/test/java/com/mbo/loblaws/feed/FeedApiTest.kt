package com.mbo.loblaws.feed

import com.google.common.truth.Expect
import com.google.common.truth.Truth.assertThat
import com.mbo.loblaws.domain.Article
import com.mbo.loblaws.http.HttpModule
import com.mbo.loblaws.http.okHttpClient
import com.mbo.loblaws.utils.TestWebServer
import com.mbo.loblaws.utils.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Rule
import org.junit.Test
import retrofit2.create
import kotlin.time.ExperimentalTime

@ExperimentalTime
class FeedApiTest {
    @get:Rule val expect = Expect.create()!!
    @get:Rule val server = TestWebServer()

    private val feedApi = HttpModule.provideRetrofit(
        okHttpClient(),
        server.baseUrl(),
        HttpModule.provideMoshi(FeedModule.provideFeedJsonAdapters())
    ).create<FeedApi>()

    @Test
    fun `should deserialize Feed`() = runBlockingTest {
        server.setDispatcher {
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                    {
                        "kind": "Listing", 
                        "data": {
                            "modhash": "", 
                            "dist": 0, 
                            "children": []
                        }
                    }
                    """.trimIndent()
                )
        }

        val feed = feedApi.getFeed()
        assertThat(feed.articles).isEmpty()
    }


    @Test
    fun `should deserialize Articles`() = runBlockingTest {
        server.setDispatcher {
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                    {
                        "kind": "Listing", 
                        "data": {
                            "modhash": "", 
                            "dist": 1, 
                            "children": [
                                {
                                    "kind": "t3",
                                    "data": {
                                        "approved_at_utc": null,
                                        "subreddit": "Kotlin",
                                        "selftext": "Hey folks, I have a question that in my team we have different opinions about this question. \n\nIs it better to use a KT file that holds more than one class or not?  for example, in android, I have a file that holds an Adapter class and its ViewHolder class. A friend of mine says that breaks the single responsibility in SOLID. Can you tell me about what do you think?",
                                        "author_fullname": "t2_14qm6o8p",
                                        "saved": false,
                                        "mod_reason_title": null,
                                        "gilded": 0,
                                        "clicked": false,
                                        "title": "Koltin with more than one class in the same file, is this break the single responsibility concept?",
                                        "link_flair_richtext": [ ],
                                        "subreddit_name_prefixed": "r/Kotlin",
                                        "hidden": false,
                                        "pwls": 6,
                                        "link_flair_css_class": null,
                                        "downs": 0,
                                        "hide_score": false,
                                        "name": "t3_fftmpv",
                                        "quarantine": false,
                                        "link_flair_text_color": "dark",
                                        "author_flair_background_color": null,
                                        "subreddit_type": "public",
                                        "ups": 8,
                                        "total_awards_received": 0,
                                        "media_embed": { },
                                        "author_flair_template_id": null,
                                        "is_original_content": false,
                                        "user_reports": [ ],
                                        "secure_media": null,
                                        "is_reddit_media_domain": false,
                                        "is_meta": false,
                                        "category": null,
                                        "secure_media_embed": { },
                                        "link_flair_text": null,
                                        "can_mod_post": false,
                                        "score": 8,
                                        "approved_by": null,
                                        "author_premium": false,
                                        "thumbnail": "",
                                        "edited": false,
                                        "author_flair_css_class": null,
                                        "author_flair_richtext": [ ],
                                        "gildings": { },
                                        "content_categories": null,
                                        "is_self": true,
                                        "mod_note": null,
                                        "created": 1583784284.0,
                                        "link_flair_type": "text",
                                        "wls": 6,
                                        "removed_by_category": null,
                                        "banned_by": null,
                                        "author_flair_type": "text",
                                        "domain": "self.Kotlin",
                                        "allow_live_comments": false,
                                        "selftext_html": "&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Hey folks, I have a question that in my team we have different opinions about this question. &lt;/p&gt;\n\n&lt;p&gt;Is it better to use a KT file that holds more than one class or not?  for example, in android, I have a file that holds an Adapter class and its ViewHolder class. A friend of mine says that breaks the single responsibility in SOLID. Can you tell me about what do you think?&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;",
                                        "likes": null,
                                        "suggested_sort": null,
                                        "banned_at_utc": null,
                                        "view_count": null,
                                        "archived": false,
                                        "no_follow": true,
                                        "is_crosspostable": false,
                                        "pinned": false,
                                        "over_18": false,
                                        "all_awardings": [ ],
                                        "awarders": [ ],
                                        "media_only": false,
                                        "can_gild": false,
                                        "spoiler": false,
                                        "locked": false,
                                        "author_flair_text": null,
                                        "visited": false,
                                        "removed_by": null,
                                        "num_reports": null,
                                        "distinguished": null,
                                        "subreddit_id": "t5_2so2r",
                                        "mod_reason_by": null,
                                        "removal_reason": null,
                                        "link_flair_background_color": "",
                                        "id": "fftmpv",
                                        "is_robot_indexable": true,
                                        "report_reasons": null,
                                        "author": "sh3lan93",
                                        "discussion_type": null,
                                        "num_comments": 9,
                                        "send_replies": true,
                                        "whitelist_status": "all_ads",
                                        "contest_mode": false,
                                        "mod_reports": [ ],
                                        "author_patreon_flair": false,
                                        "author_flair_text_color": null,
                                        "permalink": "/r/Kotlin/comments/fftmpv/koltin_with_more_than_one_class_in_the_same_file/",
                                        "parent_whitelist_status": "all_ads",
                                        "stickied": false,
                                        "url": "https://www.reddit.com/r/Kotlin/comments/fftmpv/koltin_with_more_than_one_class_in_the_same_file/",
                                        "subreddit_subscribers": 27898,
                                        "created_utc": 1583755484.0,
                                        "num_crossposts": 0,
                                        "media": null,
                                        "is_video": false
                                    }
                                }
                            ]
                        }
                    }
                    """.trimIndent()
                )
        }

        val feed = feedApi.getFeed()
        assertThat(feed.articles).containsExactly(
            Article(
                id = "fftmpv",
                title = "Koltin with more than one class in the same file, is this break the single responsibility concept?",
                body = "Hey folks, I have a question that in my team we have different opinions about this question. <br /><br />Is it better to use a KT file that holds more than one class or not?  for example, in android, I have a file that holds an Adapter class and its ViewHolder class. A friend of mine says that breaks the single responsibility in SOLID. Can you tell me about what do you think?",
                thumbnailUrl = null
            )
        )
    }
}