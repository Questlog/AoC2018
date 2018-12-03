package de.questlog.aoc2018.day01

class ChronalCalibration {
    fun calibrate(input : String) : Pair<Int, Int> {
        val lines =  input.lines().filterNot { it.isEmpty() }
        val first = lines.map {it.toInt()}.sum()

        val changes = lines.map { it.toInt() }.toList()
        val frequencies = ArrayList<Int>()
        var current = 0

        repeat@while(true){
            for( change in changes ){
                current += change

                if(frequencies.contains(current))
                    break@repeat

                frequencies.add(current)
            }
        }

        return Pair(first, current)
    }
}