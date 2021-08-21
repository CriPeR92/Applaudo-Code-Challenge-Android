package com.example.applaudocodechallengeandroid.model

import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("data") var data: ArrayList<Genre>?,
    @SerializedName("meta") var meta: Meta?,
    @SerializedName("links") var links: Links?,
)

class Genre(
    @SerializedName("id") var id: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("links") var links: Any?,
    @SerializedName("attributes") var attributes: GenreAttributes?,
)

class GenreAttributes(
    @SerializedName("createdAt") var createdAt: String?,
    @SerializedName("updatedAt") var updatedAt: String?,
    @SerializedName("slug") var slug: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("name") var name: String?,
)
