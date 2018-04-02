package com.mkhaleghy.cinemakt

data class Album(val title: String, val year: Int, val chartUk: Int, val chartUs: Int, val tracks: List<Track> = listOf())
data class Track(val title: String, val durationInSecound: Int)

val albums = listOf<Album>(
        Album("the dark side of the moon", 1973, 2, 1,
                listOf(Track("Speek to me", 90)
                        , Track("on the run", 163)
                        , Track("time", 216)
                        , Track("the great gig in the sky", 421)
                        , Track("mony", 276)
                        , Track("us and them", 382)
                        , Track("any color you like", 462)
                        , Track("brayan damage", 205)
                        , Track("eclipse", 228)
                ))
        , Album("the wall", 1979, 3, 1)
        , Album("wish you were here", 1975, 1, 1)
        , Album("animals", 1977, 2, 3)
        , Album("the piper at the gates of down", 1969, 5, 74)
        , Album("the wall", 1968, 9, 0)
)

fun albumAndTracksLowerThanXSec(duration: Int, albums: List<Album>): List<Pair<String, String>> {
    val tracks: List<Track> = listOf()

    tracks.map {
        it.durationInSecound > duration
    }.map { }

//
//    albums.map {
//        var albumTitle = it.title
//        it.tracks.filter {
//            it.durationInSecound > duration
//        }.map { Pair(albumTitle, it.title) }
//
//    }



    return albums.flatMap {
        val albumTitle = it.title
        it.tracks.filter {
            it.durationInSecound > duration
        }
                .map { Pair(albumTitle, it.title) }

    }

//    val res:List<Pair<String,String>> = listOf()
//    return res

}


fun main(args: Array<String>) {

    val list: List<String> = listOf()
    val mutableList = mutableListOf<String>()

    mutableList.add("")

    val map: List<Pair<String, Int>> = albums.filter {
        it.chartUk == 1
    }.map { Pair(it.title, it.year) }

    map.get(0).first

}
