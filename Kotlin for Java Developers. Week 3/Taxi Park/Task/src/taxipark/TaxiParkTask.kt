package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    // get a list of driver in each trip
    val d = this.trips.map { it.driver }
    // filter driver that did not make a trip(by filter non-trip-performed driver)
    // as we have list of driver that made a trip so if driver is not in them so he is a fake
    return this.allDrivers.filter { it !in d }.toSet()
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    // challenge is with passenger that not took 0 trip
    // get list of all passenger
    val allPass: Set<Passenger> = this.allPassengers
    // flat all passengers
    val pass = this.trips.flatMap { it.passengers }
    // group them by its passenger
    val grouped = pass.groupBy { it }
    // filter them return a set pf passenger
    val filteredPassenger: Set<Passenger> = grouped.filter { (_, list) ->
        list.size >= minTrips
    }.keys
    // case for 0-trip, add passengers if minTrips equal 0
    val res = allPass.filter { it in filteredPassenger || minTrips == 0 }
    // last convert list to Set
    return res.toSet()
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    // filter driver to specified driver then flat all passengers
    val pass = this.trips.filter { it.driver == driver }.flatMap { it.passengers }
    // filter passengers as count is more than 1
    val a = pass.groupBy {it}.filter { (_,list) -> list.size > 1 }.keys
    return a.toSet()
}


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    // filter trips to discounted trip  then flat all passengers
    return this.trips
            .filter { it.discount != null }
            .flatMap { it.passengers } // map trip into pass then flat it
            .groupBy { it} // group each pass with its trips
            .maxBy { (_, t) -> t.size} // get max one by max of count of trips
            ?.value!! // get value
            .toSet() // convert to set
}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    TODO()
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    TODO()
}