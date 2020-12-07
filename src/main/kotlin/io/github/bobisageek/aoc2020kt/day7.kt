package io.github.bobisageek.aoc2020kt

typealias Day7Suitcases = Map<String, Map<String, Int>>

fun main() {
    val parsedInput = Day7.parseInput(dayLines("7"))
    println(Day7.part1(parsedInput, "shiny gold"))
    println(Day7.part2(parsedInput, "shiny gold"))
}

object Day7 {
    fun parseInput(lines: List<CharSequence>) = lines.regexExtract(Regex("(.*) bags contain (.*)."))
        .filterNotNull().map { (color, contents) ->
            contents.split(", ").regexExtract(Regex("(\\d+) (.*) bags?"))
                .filterNotNull().map { (num, c) -> c to num.toInt() }.toMap().let { color to it }
        }.toMap()

    fun part1(input: Day7Suitcases, color: String): Int {
        val swapped = input.entries.fold(emptyMap<String, Set<String>>()) { acc, (color, contents) ->
            contents.entries.fold(acc) { m, (c, _) -> m + (c to (m[c] ?: emptySet()) + color) }
        }

        tailrec fun findPaths(s: Set<String>): Int {
            val t = s.union(s.flatMap { swapped[it] ?: emptySet() })
            return if (s == t) t.size else findPaths(t)
        }
        return findPaths(swapped[color] ?: emptySet())
    }

    fun part2(input: Day7Suitcases, color: String): Int {
        val thisLayer = input[color] ?: emptyMap()
        return if (thisLayer.isEmpty()) 0
        else (thisLayer.map { (c, qty) -> (part2(input, c) + 1) * qty }).sum()
    }
}
