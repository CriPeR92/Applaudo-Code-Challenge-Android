package com.example.applaudocodechallengeandroid.model

import com.google.gson.annotations.SerializedName

class MangaChapterResponse(
    @SerializedName("data") var data: ArrayList<Chapter>?,
    @SerializedName("meta") var meta: Meta?,
    @SerializedName("links") var links: Links?
)

class Chapter(
    @SerializedName("id") var id: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("links") var links: Any?,
    @SerializedName("attributes") var attributes: ChapterAttributes?,
    @SerializedName("relationships") var relationships: Any?,
)

class ChapterAttributes(
    @SerializedName("createdAt") var createdAt: String?,
    @SerializedName("updatedAt") var updatedAt: String?,
    @SerializedName("synopsis") var synopsis: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("titles") var titles: Titles?,
    @SerializedName("canonicalTitle") var canonicalTitle: String?,
    @SerializedName("volumeNumber") var volumeNumber: Int?,
    @SerializedName("number") var number: Int?,
    @SerializedName("published") var published: Int?,
    @SerializedName("length") var length: Int?,
    @SerializedName("thumbnail") var thumbnail: Any?,
)