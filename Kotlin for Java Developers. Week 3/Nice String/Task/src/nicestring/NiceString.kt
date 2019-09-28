package nicestring

fun String.isNice(): Boolean {
    var count = 0 ;
    // not contains bu, ba or be
    if (!("ba" in this || "bu" in this || "be" in this )) count++
    // 3 vowels
    val c = filter { it in "aeoiu" }.count()
    if (c>=3) count++
    // doubles
    for ((i ,j) in  zipWithNext()) if (i == j) count++

    return count >= 2
}



