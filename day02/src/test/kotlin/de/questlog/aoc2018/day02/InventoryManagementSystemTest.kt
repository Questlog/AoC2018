package de.questlog.aoc2018.day02;

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class InventoryManagementSystemTest {

    private lateinit var puzzleInput : String

    @Before
    fun setUp(){
        puzzleInput = this::class.java.classLoader.getResource("Day02Input.txt").readText()
    }

    @Test
    fun scanTest(){
        val inventoryManagementSystem = InventoryManagementSystem()

        var input = "abcdef,bababc,abbcde,abcccd,aabcdd,abcdee,ababab".replace(",", "\r\n")
        var result = inventoryManagementSystem.scan(input)
        assertEquals(12, result.first)

        input = "abcde\n" +
                "fghij\n" +
                "klmno\n" +
                "pqrst\n" +
                "fguij\n" +
                "axcye\n" +
                "wvxyz"
        result = inventoryManagementSystem.scan(input)
        assertEquals("fgij", result.second)

        result = inventoryManagementSystem.scan(puzzleInput)
        println(result)
    }
}
