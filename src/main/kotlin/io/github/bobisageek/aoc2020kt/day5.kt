package io.github.bobisageek.aoc2020kt

import java.util.*

fun main() {
    val seatNumberSet = dayLines("5").map(Day5::seatNum).toSortedSet()
    println(Day5.part1(seatNumberSet))
    println(Day5.part2(seatNumberSet))
}

object Day5 {
    fun seatNum(seatCode: String) = seatCode.map {
        mapOf('B' to 1, 'R' to 1).getOrDefault(it, 0)
    }.joinToString(separator = "").let { Integer.parseInt(it, 2) }

    fun part1(input: SortedSet<Int>) = input.last()
    fun part2(input: SortedSet<Int>) = input.zip(input.drop(1)).first { (a, b) -> b - a != 1 }.let { it.first + 1 }
}
