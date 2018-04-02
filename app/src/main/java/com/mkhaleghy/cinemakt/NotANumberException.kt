package com.mkhaleghy.cinemakt


class NotANumberException(msg: String) :Throwable(msg){

}

fun checkNumber(obj: Any){
    when(obj){
        !is Int,Double,Float,Long-> throw NotANumberException("this is not a number")
    }
}

fun main(args: Array<String>) {

    val number="10"
    try {
        checkNumber(number)
    }catch (e:NotANumberException){
        println("${e.message}")
    }

}