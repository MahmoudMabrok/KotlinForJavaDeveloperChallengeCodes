package rationals

import java.math.BigInteger

class Rational(val numerator: BigInteger, val donmerator: BigInteger) {
    override fun toString(): String {
        return ("$numerator/$donmerator").toString()
    }

    override fun equals(other: Any?): Boolean {
        return numerator.equals((other as? Rational)?.numerator)
    }
}

fun String.toRational(): Rational {
    val pos = this.indexOf('/')
    return if (pos != -1) {
        val n = this.substring(0, pos).toBigInteger()
        val d = this.substring(pos + 1).toBigInteger()
        Rational(n, d)
    } else {
        val n = this.toBigInteger()
        val d = "1".toBigInteger()
        Rational(n, d)
    }
}

// use infix modifier
infix fun Int.divBy(other: Int): Rational = Rational(this.toBigInteger(), other.toBigInteger())

infix fun Long.divBy(other: Long): Rational = Rational(this.toBigInteger(), other.toBigInteger())
infix fun BigInteger.divBy(other: BigInteger): Rational = Rational(this, other)

operator fun Rational.plus(other: Rational): Rational {
    val newNumerator = (numerator.multiply(other.donmerator)).plus((donmerator.multiply(other.numerator)))
    return Rational(newNumerator, this.donmerator.multiply(other.donmerator))
}

operator fun Rational.minus(other: Rational): Rational {
    val newNumerator = (numerator.multiply(other.donmerator)).minus((donmerator.multiply(other.numerator)))
    return Rational(newNumerator, this.donmerator.multiply(other.donmerator))
}
operator fun Rational.times(other: Rational): Rational =
        Rational(this.numerator.multiply(other.numerator),
                this.donmerator.multiply(other.donmerator))

operator fun Rational.div(other: Rational): Rational {
    return Rational(this.numerator.div(other.numerator),
            this.donmerator.multiply(other.donmerator))
}

operator fun Rational.unaryMinus(): Rational = Rational(numerator.multiply(-1.toBigInteger())
        , donmerator)

// todo check add, sub , div with cond

fun main() {

    println("21/6".toRational())
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    /*
    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
*/

}