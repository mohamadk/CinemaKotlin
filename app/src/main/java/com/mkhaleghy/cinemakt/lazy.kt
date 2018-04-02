package com.mkhaleghy.cinemakt


fun main(args: Array<String>) {

    val elements = 1..1000000

//    val output=elements.filter { it < 30 }.map { Pair("Yes", it) }
//    println("midLine")
//    output.forEach{
//        println(it)
//    }

    val output1=elements.asSequence().filter { it < 30 }.map { Pair("Yes", it) }
    println("midLine")
    output1.forEach{
        println(it)
    }

//    val output2=elements.asSequence().take(40).filter { it < 30 }.map { Pair("Yes", it) }

//    val classes= generateSequence(Album("",1,1,1)) {x-> Album(x.title,1,1,1) }

//    val lazyInit:Int by lazy { 20 }

}