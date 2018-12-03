package de.questlog.aoc2018.day02

class InventoryManagementSystem {
    fun scan(input : String) : Pair<Int, String> {
        val lines = input.lines()

        val letterCounts = HashMap<Int, Int>()

        for(line in lines){
            val groupedByCount = line.toSortedSet().groupBy {char ->
                line.count { it == char }
            }.filter { it.key != 1 }

            for(charCount in groupedByCount) {
                letterCounts.compute(charCount.key) { k, v ->
                    (v ?: 0) + 1
                }
            }
        }

        val checkSum = letterCounts.run {
            if (!isEmpty())
                map { it.value }.reduce { acc, i -> acc * i }
            else
                -1
        }


        var commonChars = ""
        for(line1 in lines) {
            for (line2 in lines) {
                if (line1 == line2)
                    continue
                val prefix = line1.commonPrefixWith(line2)
                val suffix = line1.commonSuffixWith(line2)
                if (prefix.length + suffix.length + 1 == line1.length) {
                    commonChars = prefix + suffix
                }
            }
        }

        return Pair(checkSum, commonChars)
    }
}

