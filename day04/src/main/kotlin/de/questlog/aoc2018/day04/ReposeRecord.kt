package de.questlog.aoc2018.day04

import java.util.*
import kotlin.collections.ArrayList

class ReposeRecord {
    fun sneak(input: String) : Pair<Int, Int> {

        val lines = input.lines().sortedBy {
            it.substringAfter("[").substringBefore("]")
        }

        var state: State
        val guardsById = HashMap<Int, Guard>()
        var currentGuard = Guard(0, ArrayList())
        var startSleepTime = 0

        for(line in lines){
            state = when {
                line.endsWith("begins shift") -> State.ShiftChange
                line.endsWith("falls asleep") -> State.FallsAsleep
                else -> State.WakesUp
            }

            if(state == State.ShiftChange) {
                val id = Regex("#(\\d+)").find(line)!!.groupValues[1].toInt()
                currentGuard = guardsById.computeIfAbsent(id) { Guard(id, ArrayList()) }
            }

            if(state == State.FallsAsleep) {
                startSleepTime = Regex(":(\\d+)]").find(line)!!.groupValues[1].toInt()
                val hour = Regex("(\\d+):").find(line)!!.groupValues[1].toInt()
                if(hour == 23)
                    startSleepTime = 0
            }

            if (state == State.WakesUp) {
                val stopSleepTime = Regex(":(\\d+)]").find(line)!!.groupValues[1].toInt()

                for(i in startSleepTime until stopSleepTime){
                    currentGuard.minutesAsleep.add(i)
                }
            }
        }

        val mostMinutes = guardsById.map { it.value }
            .maxBy { it.minutesAsleep.size }
        val bestMinute = mostMinutes?.minutesAsleep?.groupBy { it }?.maxBy { it.value.size }?.key
        val result = (mostMinutes?.id ?: 0) * (bestMinute ?: 0)

        var max = 0
        var guardSameMinute = guardsById.values.first()
        var theMinute = 0
        for (guard in guardsById.values) {
            val mostMintues = guard.minutesAsleep.groupBy { it }.maxBy { it.value.size }
            if (mostMintues != null) {
                val count = mostMintues.value.size
                if(count > max){
                    max = count
                    guardSameMinute = guard
                    theMinute = mostMintues.key
                }
            }
        }

        val resultPartTwo = guardSameMinute.id * theMinute

        return Pair(result, resultPartTwo)
    }
}

data class Guard(
    val id: Int,
    val minutesAsleep: ArrayList<Int>
)

enum class State{
    ShiftChange, FallsAsleep, WakesUp
}