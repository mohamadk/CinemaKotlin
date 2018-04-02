package com.mkhaleghy.cinemakt


    class Service() {
        val a = "a"
        val b = 10
        val nullv = null;
        val nullString: String? = null
        fun run() {
            println("run service")
        }
    }

    class man {
        val service: Service? = Service()
    }




    fun main(args: Array<String>?) {
        val m = man()
        println(m.service?.nullString?.length)



    }
