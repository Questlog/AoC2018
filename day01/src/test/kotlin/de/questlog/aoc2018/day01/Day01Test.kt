package de.questlog.aoc2018.day01

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Day01Test {

    private lateinit var puzzleInput : String

    @Before
    fun setUp(){
        puzzleInput = this::class.java.classLoader.getResource("Day01Input.txt").readText()
    }

    @Test
    fun solvePartOne(){
        val chronalCalibration = ChronalCalibration()

        //part1
        var testData = "+1\r\n+1\r\n-2"
        var result = chronalCalibration.calibrate(testData)
        assertEquals(0, result.first)

        //part2
        testData = "+3, +3, +4, -2, -4".replace(", ","\n")
        result = chronalCalibration.calibrate(testData)
        assertEquals(10, result.second)

        result = chronalCalibration.calibrate(puzzleInput)
        println(result)
    }
}