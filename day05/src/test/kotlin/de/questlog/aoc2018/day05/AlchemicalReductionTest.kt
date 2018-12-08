package de.questlog.aoc2018.day05

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class AlchemicalReductionTest {
    private lateinit var puzzleInput : String

    @Before
    fun setUp(){
        puzzleInput = this::class.java.classLoader.getResource("day05Input.txt").readText()
    }

    @Test
    fun reduceTest(){
        val alchemicalReduction = AlchemicalReduction()
        var input = "dabAcCaCBAcCcaDA"
        var result = alchemicalReduction.reduce(input)
        assertEquals(10, result.first)
        assertEquals(4, result.second)

        result = alchemicalReduction.reduce(puzzleInput)
        println(result)
    }
}