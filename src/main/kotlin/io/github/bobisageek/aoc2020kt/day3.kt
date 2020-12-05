package io.github.bobisageek.aoc2020kt

data class Day3TreeMap(
    val width: Int,
    val lines: List<Set<Int>>
)

fun main() {
    val input = dayLines("3")
    val parsed = Day3.parseInputLines(input)
    println(Day3.part1(parsed))
    println(Day3.part2(parsed))
}

object Day3 {
    fun part1(input: Day3TreeMap) = countCollisions(input, 3, 1)
    fun part2(input: Day3TreeMap) = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2).map { (right, down) ->
        countCollisions(input, right, down)
    }.map { it.toLong() }.reduce { a, b -> a * b }

    fun parseInputLines(lines: List<String>) = Day3TreeMap(
        width = lines[0].length,
        lines = lines.map { it.foldIndexed(emptySet<Int>()) { index, set, c -> if (c == '#') set + index else set } }
    )

    fun countCollisions(map: Day3TreeMap, right: Int, down: Int): Int {
        val (width, lines) = map
        val indexSeq = generateSequence(0) { (it + right) % width }
        return indexSeq.zip(lines.filterIndexed { index, _ -> index % down == 0 }
            .asSequence()) { index, set -> index in set }.count { it }
    }
}
