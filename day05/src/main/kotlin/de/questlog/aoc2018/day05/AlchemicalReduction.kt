package de.questlog.aoc2018.day05

class AlchemicalReduction {
    fun reduce(input : String) : Pair<Int, Int> {

        var best = Int.MAX_VALUE
        for(abc in CharRange('a', 'z')){
            val length = input
                .replace("$abc", "")
                .replace("${abc.toUpperCase()}", "")
                .reduce().length
            if(length < best)
                best = length
            println("$abc , $length")
        }

        return Pair(input.reduce().length, best)
    }
}

fun String.reduce() : String {
    var polymer = this
    var pairAt = this.findPair()
    while (pairAt != null){
        polymer = polymer.removeRange(pairAt, pairAt + 2)
        pairAt = polymer.findPair()
    }
    return polymer
}

fun String.findPair() : Int? {
    for(i in 0 until this.length -1) {
        val a = this[i]
        val b = this[i + 1]
        if(a.isUpperCase() && a.toLowerCase() == b ||
                b.isUpperCase() && b.toLowerCase() == a){
            return i
        }
    }
    return null
}