package com.mkhaleghy.cinemakt

open class Person{

    open fun validate(){

    }
}

abstract class AC{

}

open class Customer1 :Person(){
    override fun validate(){
        super.validate()
        val a=10

    }


}


fun main(args: Array<String>) {


}
