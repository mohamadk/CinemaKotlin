package com.mkhaleghy.cinemakt.main

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mkhaleghy.cinemakt.base.Element
import kotlinx.android.parcel.Parcelize

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
        , override val type: Int) : Element()

@Parcelize
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
        val detailInfoPages: List<InfoPages>
        , @SerializedName("ipt")
        val detailInfoPagesTitles: List<String>) : Parcelable

@Parcelize
data class BookTime(
        @SerializedName("pt")
        val prefixTitle: String
        , @SerializedName("t")
        val title: String
        , @SerializedName("i")
        val id: Int
) : Parcelable {
    override fun toString(): String {
        return prefixTitle + " " + title
    }
}

data class DetailInfoPage(
        @SerializedName("t")
        val title: String
        , @SerializedName("i")
        val info: InfoPages
)

@Parcelize
data class InfoPages(
        @SerializedName("t")
        val title: String
        , @SerializedName("c")
        val content: String
) : Parcelable

