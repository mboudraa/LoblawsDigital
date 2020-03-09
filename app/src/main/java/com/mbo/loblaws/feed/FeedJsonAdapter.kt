package com.mbo.loblaws.feed

import com.mbo.loblaws.domain.Article
import com.mbo.loblaws.domain.Feed
import com.mbo.loblaws.http.readObject
import com.mbo.loblaws.http.skipNameAndValue
import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class FeedWrapper(val children: List<ArticleWrapper>)

class FeedJsonAdapter {

    @FromJson
    fun fromJson(
        reader: JsonReader,
        feedAdapter: JsonAdapter<FeedWrapper>
    ): Feed {
        lateinit var wrapper: FeedWrapper
        reader.readObject {
            when (reader.selectName(NAMES)) {
                Field.DATA.ordinal -> wrapper = feedAdapter.fromJson(reader)!!
                else -> reader.skipNameAndValue()
            }
        }
        return Feed(wrapper.children.map { it.data })
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Feed?) {
        throw UnsupportedOperationException()
    }

    companion object {
        private val NAMES =
            JsonReader.Options.of(*Array(Field.values().size) { Field.values()[it].value })
    }

    private enum class Field(val value: String) {
        DATA("data")
    }
}

