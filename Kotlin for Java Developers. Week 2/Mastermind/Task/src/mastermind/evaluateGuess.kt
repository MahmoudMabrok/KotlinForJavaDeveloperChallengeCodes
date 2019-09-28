package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var overMatched = 0 ;
    var correct = 0
    var c = guess.toMutableList()
    var d = secret.toMutableList()


    var t  = 0 ;
    // first get nCorrect
    for(i in c.indices){
        if (c.get(i) == d.get(i)) {
            correct ++
            d.set(i , '#')
            c.set(i , '#')

        }
    }
    // get wrong placed
    for(i in c.indices){
        if (c.get(i) != '#') {
            t = d.indexOf(c.get(i))
            if (t != -1 ) {
                overMatched ++
                d.set(t , '#')
            }
        }
    }
    return Evaluation(correct,overMatched)
}
