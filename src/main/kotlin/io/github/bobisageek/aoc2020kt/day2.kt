package io.github.bobisageek.aoc2020kt

typealias day2line = Triple<Pair<Int, Int>, Char, String>

fun main() {
    val rgx = Regex("^([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)$")
    val input = dayLines("2").regexExtract(rgx).filterNotNull()
    val parsed = input.map { (num1, num2, char, pass) ->
        Triple((num1.toInt() to num2.toInt()), char.first(), pass)
    }
    println(part1(parsed))
    println(part2(parsed))
}

fun part1(input: List<day2line>) = input.count { (min_max, char, pass) ->
    pass.count { it == char } in (min_max.first..min_max.second)
}

fun part2(input: List<day2line>) = input.count { (min_max, char, pass) ->
    val (f, s) = min_max.toList().map { it - 1 }
    (pass[f] == char).xor(pass[s] == char)
}
