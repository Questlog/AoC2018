package de.questlog.aoc2018.day03

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SlicerTest {
    private lateinit var puzzleInput : String

    @Before
    fun setUp(){
        puzzleInput = this::class.java.classLoader.getResource("Day03Input.txt").readText()
    }

    @Test
    fun sliceTest(){
        var input = "#1 @ 1,3: 4x4\n" +
                "#2 @ 3,1: 4x4\n" +
                "#3 @ 5,5: 2x2"
        val slicer = Slicer()

        var result = slicer.slice(input, 8, 8)
        assertEquals(4, result.first)
        assertEquals(3, result.second)

        result = slicer.slice(puzzleInput, 1000, 1000)
        println(result)
    }
}