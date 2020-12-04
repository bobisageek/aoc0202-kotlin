package io.github.bobisageek.aoc2020kt

fun main() {
    val input = dayLines("1").map { it.toInt() }
    println(part1(input, 2020))
    println(part2(input, 2020))
}

tailrec fun part1(ints: List<Int>, target: Int, complements: Map<Int, Int> = emptyMap()): Int {
    if (ints.isEmpty()) return 0
    val head = ints.first()
    complements[head]?.let { if (head + it == target) return head * it }
    return part1(ints.drop(1), target, complements + ((target - head) to head))
}

tailrec fun part2(ints: List<Int>, target: Int): Int {
    if (ints.isEmpty()) return 0
    val head = ints.first()
    val theRest = part1(ints.drop(1), target - head)
    return if (theRest != 0) theRest * head else part2(ints.drop(1), target)
}
