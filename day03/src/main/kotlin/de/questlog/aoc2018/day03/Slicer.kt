package de.questlog.aoc2018.day03

class Slicer {
    fun slice(input: String, width: Int, height: Int) : Pair<Int, Int>{
        val lines = input.lines()

        val grid = Array(width) { Array(height) { ArrayList<Claim>() } }

        val claims = lines.map {
            val match = Regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)").matchEntire(it)
            Claim(
                match!!.groupValues[1].toInt(),
                match.groupValues[2].toInt(),
                match.groupValues[3].toInt(),
                match.groupValues[4].toInt(),
                match.groupValues[5].toInt(),
                false
            )
        }

        claims.forEach {
            for(x in it.left until it.left+it.width) {
                for(y in it.top until it.top+it.height) {
                    grid[x][y].add(it)
                    if(grid[x][y].size > 1) {
                        for(claim in grid[x][y]) {
                            claim.overlaps = true
                        }
                    }
                }
            }
        }

        var overusedInches = 0
        for(row in grid) {
            for(value in row) {
                if(value.size > 1) {
                    overusedInches += 1
                }
            }
        }

        val notOverlappingClaim = claims.first { !it.overlaps }

        return Pair(overusedInches, notOverlappingClaim.id)
    }
}

data class Claim (
    val id: Int,
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int,
    var overlaps : Boolean
)