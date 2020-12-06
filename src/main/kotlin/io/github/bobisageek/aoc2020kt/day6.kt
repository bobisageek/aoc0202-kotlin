package io.github.bobisageek.aoc2020kt

typealias Day6SurveyList = List<List<Set<Char>>>

fun main() {
    val surveys = Day6.parseInput(dayText("6"))
    println(Day6.aggregate(surveys) { a, b -> a.union(b) })
    println(Day6.aggregate(surveys) { a, b -> a.intersect(b) })
}

object Day6 {
    fun parseInput(input: String) = input.split(Regex("(\\r?\\n)\\1")).map {
        it.lines().map { it.toSet() }
    }

    fun aggregate(surveyList: Day6SurveyList, op: (a: Set<Char>, b: Set<Char>) -> Set<Char>) = surveyList.map {
        it.reduce { a, b -> op(a, b) }
    }.sumBy { it.count() }
}
