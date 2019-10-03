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

    val taxiPark = taxiPark(0..6, 0..11,
            trip(3, listOf(9), duration = 4, distance = 26.0, discount = 0.3),
            trip(0, listOf(7), duration = 16, distance = 34.0, discount = 0.2),
            trip(2, listOf(9), duration = 19, distance = 16.0),
            trip(1, listOf(4, 6, 3), duration = 0, distance = 3.0),
            trip(3, listOf(6, 11), duration = 33, distance = 10.0),
            trip(3, listOf(11, 9), duration = 20, distance = 22.0),
            trip(1, listOf(3, 4), duration = 18, distance = 19.0),
            trip(3, listOf(4, 7), duration = 0, distance = 31.0, discount = 0.3),
            trip(0, listOf(8, 7), duration = 7, distance = 14.0),
            trip(0, listOf(11, 7, 5, 8), duration = 4, distance = 1.0, discount = 0.4),
            trip(3, listOf(4, 8, 1), duration = 35, distance = 2.0),
            trip(3, listOf(1), duration = 35, distance = 30.0),
            trip(2, listOf(6, 1), duration = 23, distance = 33.0),
            trip(3, listOf(7, 6), duration = 38, distance = 9.0),
            trip(1, listOf(3, 4, 5), duration = 2, distance = 34.0, discount = 0.2),
            trip(1, listOf(4, 8, 7), duration = 5, distance = 31.0, discount = 0.1),
            trip(0, listOf(11, 4, 6), duration = 15, distance = 2.0),
            trip(3, listOf(9, 8, 6), duration = 24, distance = 17.0),
            trip(3, listOf(0), duration = 37, distance = 3.0, discount = 0.1),
            trip(1, listOf(5, 7), duration = 0, distance = 15.0, discount = 0.4))

    var durations = taxiPark.trips
            .map{Pair(it.driver , it.cost )} // pair of driver, cost
            .groupBy { it.first } // driver , list of costs
            .mapValues { (_ , list) -> list.sumBy { it.second.toInt() } } // driver , sum of costs

    durations = durations.toMutableMap()
    // get driver with no trips
    val aa = taxiPark.allDrivers.filter { it !in durations.keys }
    // add them to map with 0
    for ( dr in aa ){
       durations.put(dr,0 )
    }

    val totalSum = durations.values.sum()
    println("sum $totalSum")

    val nOfDriver = taxiPark.allDrivers.size
    val nOfTop = (0.20 * nOfDriver).toInt() // floor it

    val incomeFromTop = durations.map { it.value}.sortedByDescending { it}.take(nOfTop).sum()
    println("n od driver  $nOfDriver ")
    println("total is $totalSum top 20% $nOfTop contain income $incomeFromTop  is : ${incomeFromTop >= (0.8 * totalSum)}")

}

