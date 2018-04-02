package com.mkhaleghy.cinemakt

/**
 * Created by mk on 3/29/2018.
 */

class GetGeneric {

    fun operator(a :Int){

    }

    fun operator(x: Int, y: Int, op: (Int, Int) -> Int): Int {
        return op(x, y)
    }

    fun operator(x: Int, op: (Int) -> Int) {
        println(x)
    }

    fun transaction(db: DataBase, code: () -> Unit) {

        try {
            code()
        } finally {
            db.commit()
        }

    }


    class DataBase {
        fun commit() {
            println("commit")
        }
    }


    fun sum(x: Int, y: Int) = x + y


    fun outsideFunction() {
        for (number in 1..30){
            operator(1,{x->
                println(x)
                x*number
            })
        }
    }

    fun multipleArgumans(vararg strings:String){

        printArgs(*strings)

    }

    private fun printArgs(vararg strings: String) {

        strings.forEach {
            println(it)
        }
    }


    fun main(args: Array<String>?) {
        operator(1, 5, ::sum)
        val op: (Int, Int) -> Int = { x, y -> x + y }
        operator(1, 5, op)
        operator(1, { it * it })
        operator(52) { it * it }

        operator(5,fun(a:Int):Int=a*a)

//        transaction(DataBase(), {
//            println("run the code")
//        })


        operator(10)


    }
}