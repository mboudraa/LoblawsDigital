package com.mbo.loblaws.domain

data class Article(
    val id: String,
    val title: String,
    val thumbnailUrl: String?,
    val body: String?
)