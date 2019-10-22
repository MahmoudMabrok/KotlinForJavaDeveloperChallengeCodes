package taxipark

fun driver(i: Int) = Driver("D-$i")
fun passenger(i: Int) = Passenger("P-$i")

fun drivers(indices: List<Int>) = indices.map(::driver).toSet()
fun drivers(range: IntRange) = drivers(range.toList())
fun drivers(vararg indices: Int) = drivers(indices.toList())

fun passengers(indices: List<Int>) = indices.map(::passenger).toSet()
fun passengers(range: IntRange) = passengers(range.toList())
fun passengers(vararg indices: Int) = passengers(indices.toList())

fun taxiPark(driverIndexes: IntRange, passengerIndexes: IntRange, vararg trips: Trip) =
        TaxiPark(drivers(driverIndexes), passengers(passengerIndexes), trips.toList())

fun trip(driverIndex: Int, passengerIndexes: List<Int>, duration: Int = 10, distance: Double = 3.0, discount: Double? = null) =
        Trip(driver(driverIndex), passengers(passengerIndexes), duration, distance, discount)

fun trip(driverIndex: Int, passenger: Int, duration: Int = 10, distance: Double = 3.0, discount: Double? = null) =
        Trip(driver(driverIndex), passengers(passenger), duration, distance, discount)

fun main() {

    val taxiPark = taxiPark(0..2, 0..2,
            trip(2, listOf(2, 1), duration = 14, distance = 10.0, discount = 0.4),
            trip(1, listOf(1, 2, 0), duration = 20, distance = 26.0),
            trip(0, listOf(2, 0), duration = 15, distance = 14.0, discount = 0.4),
            trip(0, listOf(2, 1), duration = 4, distance = 17.0, discount = 0.2),
            trip(1, listOf(0, 1, 2), duration = 35, distance = 23.0),
            trip(2, listOf(1), duration = 28, distance = 5.0),
            trip(0, listOf(0), duration = 30, distance = 25.0, discount = 0.3),
            trip(0, listOf(2, 0), duration = 24, distance = 13.0),
            trip(0, listOf(0, 2), duration = 5, distance = 5.0, discount = 0.2),
            trip(0, listOf(2), duration = 39, distance = 29.0, discount = 0.1),
            trip(1, listOf(0, 2), duration = 25, distance = 9.0),
            trip(2, listOf(2, 0), duration = 36, distance = 23.0, discount = 0.2))

    var passengers = taxiPark.allPassengers
    var data = passengers.map { Pair(it , 0.0 )}
    /*
    * some consideration
    *  majority of each passenger trips
    * so get each pass with percentage of trips with discount then
    * get max one
    * */
    var hasDiscount = taxiPark.trips
            .filter { it.discount != null }
            .flatMap { it.passengers } // map trip into pass then flat it
            .groupBy { it} // group each pass with its trips
            .map { Pair(it.key,it.value.size)}
    println(hasDiscount)
    var hasNoDiscount = taxiPark.trips
            .flatMap { it.passengers } // map trip into pass then flat it
            .groupBy { it} // group each pass with its trips
            .map { Pair(it.key,it.value.size)}
    println(hasNoDiscount)

    data = data.map {
        var has = hasDiscount.find { pass -> pass.first == it.first }?.second ?: 0
        var nohas = hasNoDiscount.find { pass -> pass.first == it.first }?.second ?: 0
        val per:Double = if (nohas+ has == 0 ) 0.0 else ((has / ((has + nohas)*1.0) ) )
        /*if (nohas != 0  && has != 0 ){
            println("-- $has $nohas $per")
        }*/
        Pair(it.first,per)
    }

    println(data)


}

