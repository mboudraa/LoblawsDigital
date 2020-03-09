package com.mbo.loblaws.feed

import com.mbo.loblaws.domain.Article
import com.mbo.loblaws.domain.Feed
import com.mbo.loblaws.http.nextStringOrNull
import com.mbo.loblaws.http.readObject
import com.mbo.loblaws.http.skipNameAndValue
import com.squareup.moshi.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.parser.Parser


@JsonClass(generateAdapter = true)
data class ArticleWrapper(val data: Article)

class ArticleJsonAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Article {
        lateinit var title: String
        var body: String? = null
        lateinit var id: String
        var thumbnail: String? = null
        reader.readObject {
            when (reader.selectName(NAMES)) {
                Field.TITLE.ordinal -> title = reader.nextString()
                Field.BODY.ordinal -> body = reader.nextStringOrNull()
                Field.ID.ordinal -> id = reader.nextString()
                Field.THUMBNAIL.ordinal -> {
                    val value = reader.nextStringOrNull()
                    thumbnail = when {
                        value.isNullOrEmpty() -> null
                        else -> value
                    }
                }
                else -> reader.skipNameAndValue()
            }
        }
        return Article(
            id = id,
            title = Parser.unescapeEntities(title, true),
            body = body?.let { Parser.unescapeEntities(it, false) }?.replace("\n", "<br />"),
            thumbnailUrl = thumbnail
        )
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
        ID("id"),
        TITLE("title"),
        BODY("selftext"),
        THUMBNAIL("thumbnail")
    }
}