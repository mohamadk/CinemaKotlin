package com.mkhaleghy.cinemakt


fun capitalInformation(country: String): Pair<String, Int> {
    return Pair("Madrid", 12000000)
}

fun countryInformation(country: String): Triple<String, String, Int> {
    return Triple("Madrid", "europe", 12000000)
}


fun main(args: Array<String>) {

    val (capital,population)= capitalInformation("Spain")

    val (id,email)=Customer(1,"name")

    val listOfCapitalAndCountries= listOf<Pair<String,String>>(Pair("Madrid","Spain"),"Paris" to "France")

    for ((capital, country) in listOfCapitalAndCountries) {

    }

}