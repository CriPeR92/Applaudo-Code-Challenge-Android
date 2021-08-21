package com.example.applaudocodechallengeandroid.model

import com.google.gson.annotations.SerializedName

class MainCharactersAnimeResponse(
    @SerializedName("data") var data: ArrayList<Anime>?,
    @SerializedName("meta") var meta: Meta?,
    @SerializedName("links") var links: Links?,
    @SerializedName("included") var included: ArrayList<AnimeCharacter>?,
)

class AnimeCharacter(
    @SerializedName("id") var id: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("links") var links: Any?,
    @SerializedName("attributes") var attributes: AnimeCharacterAttributes?,
    @SerializedName("relationships") var relationships: Any?,
)

class AnimeCharacterAttributes(
    @SerializedName("createdAt") var createdAt: String?,
    @SerializedName("updatedAt") var updatedAt: String?,
    @SerializedName("slug") var slug: String?,
    @SerializedName("names") var names: Titles?,
    @SerializedName("canonicalName") var canonicalName: String?,
    @SerializedName("otherNames") var otherNames: Array<String>?,
    @SerializedName("name") var name: String?,
    @SerializedName("malId") var malId: Int?,
    @SerializedName("description") var description: String?,
    @SerializedName("image") var image: Any?,
)