package com.mkhaleghy.cinemakt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    val a = 0
    var x = 20L
    var asd: Int = 20
    var o = "dfaf"

    var car = """
        this is multi text line
       $a $x ${a + x}
        """

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var customer1=Customer(10,"mohammad");
        var customer2=Customer(11,"mohammad1");
        var customer3=customer1.copy();


        customer1.name="mk"
        if(customer1.equals(customer3)){
            println("customer1.equals(customer3)")
        }

        if(customer1==customer3){
            println("customer1==customer3 cusstomer1=$customer1 customer3=$customer3")
        }

        println(car)



        var x=0x11;
        x=0xb101;
        x=10;

        var xx = if (a == 10) {
            11
        } else {
            12
        }

        print("a","v")


    }


    fun print(vararg strings: String): String {
        return ""
    }





}
