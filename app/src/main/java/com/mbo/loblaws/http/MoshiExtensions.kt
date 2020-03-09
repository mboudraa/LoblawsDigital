package com.mbo.loblaws.http

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi

fun JsonReader.nextStringOrNull(): String? {
    return if (peek() == JsonReader.Token.NULL) nextNull() else nextString()
}

fun JsonReader.skipNameAndValue() {
    skipName()
    skipValue()
}

inline fun JsonReader.readObject(body: () -> Unit) {
    beginObject()
    while (hasNext()) {
        body()
    }
    endObject()
}

inline fun <T : Any> JsonReader.readArrayToList(body: () -> T?): List<T> {
    val result = mutableListOf<T>()
    beginArray()
    while (hasNext()) {
        body()?.let { result.add(it) }
    }
    endArray()
    return result
}

inline fun JsonWriter.writeObject(body: JsonWriter.() -> Unit) {
    beginObject()
    body()
    endObject()
}

inline fun JsonWriter.writeArray(body: JsonWriter.() -> Unit) {
    beginArray()
    body()
    endArray()
}

inline fun <reified T> Moshi.adapter(): JsonAdapter<T> = adapter(T::class.java)
