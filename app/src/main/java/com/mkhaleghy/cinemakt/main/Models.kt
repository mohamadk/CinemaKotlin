package com.mkhaleghy.cinemakt.main

import com.google.gson.annotations.SerializedName
import com.mkhaleghy.cinemakt.base.Element

data class DayList(
        @SerializedName("m")
        val movies: List<Movie>)

data class Movie(
        @SerializedName("i")
        val id: String
        , @SerializedName("ic")
        val icon: String
        , @SerializedName("t")
        val title: String
        , @SerializedName("st")
        val subtitle: String
        , @SerializedName("g")
        val genre: String
        , @SerializedName("r")
        val rate: Float
        , @SerializedName("ti")
        val time: String
        , @SerializedName("d")
        val detail: Detail
        , override val type: Int): Element()

data class Detail(
        @SerializedName("tu")
        val thriller: String
        , @SerializedName("c")
        val cover: String
        , @SerializedName("i")
        val icon: String
        , @SerializedName("ti")
        val bookTimes: List<BookTime>
        , @SerializedName("t")
        val title: String
        , @SerializedName("st")
        val subtile: String
        , @SerializedName("r")
        val rate: Float
        , @SerializedName("rt")
        val rateText: String
        , @SerializedName("ip")
        val detailInfoPages: List<InfoPages>)

data class BookTime(
        @SerializedName("pt")
        val prefixTitle: String
        , @SerializedName("t")
        val title: String
        , @SerializedName("i")
        val id: Int
)

data class InfoPages(
        @SerializedName("t")
        val title: String
        , @SerializedName("c")
        val content: String
)

