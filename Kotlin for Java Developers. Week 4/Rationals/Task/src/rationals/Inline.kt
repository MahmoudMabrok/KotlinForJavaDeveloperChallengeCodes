package rationals
fun main() {
    val list = listOf(1, 2, 3, 4)
    list.asSequence().map(::m).filter(::f)
    list.asSequence().map(::m).filter(::f).toList()
    list.asSequence().filter(::f).map(::m).toList()
}
fun m(i: Int): Int {
    print("m$i ")
    return i
}
fun f(i: Int): Boolean {
    print("f$i ")
    return i % 2 == 0
}


/*
 list.asSequence().map(::m).filter(::f) // Nothing
list.asSequence().map(::m).filter(::f).toList() // m1 f1 m2 f2 m3 f3 m4 f4
list.asSequence().filter(::f).map(::m).toList() // f1 f2 m2 f3 f4 m4
*/

